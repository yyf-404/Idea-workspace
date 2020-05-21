package com.yyf.mallcache.service.impl;


import java.util.Date;
import java.util.List;


import com.yyf.mallcache.bean.*;
import com.yyf.mallcache.mapper.*;
import com.yyf.mallcache.mq.MQSender;
import com.yyf.mallcache.redis.ProductKey;
import com.yyf.mallcache.redis.UserKey;
import com.yyf.mallcache.service.RedisService;
import com.yyf.mallcache.service.SecService;
import com.yyf.mallcache.util.ConfigUtil;
import com.yyf.mallcache.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class SecServiceImpl implements SecService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    SecProductMapper secProductMapper;
    @Autowired
    SecOrderMapper secOrderMapper;
    @Autowired
    AddressMapper addressMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private MQSender sender;

    @Override
    public String toSec(Integer userId, Integer secproductId) {
        String returnStr = validSec(userId, secproductId);
        // 验证失败
        if (returnStr != ConfigUtil.SEC_SUCCESS) {
            return returnStr;
        }
        // 入队操作
        SecMessage secMessage = new SecMessage();
        secMessage.setProductId(secproductId);
        secMessage.setUserId(userId);
        sender.send(secMessage);
        return ConfigUtil.SEC_WAIT;// 排队中
    }

    /**
     * 下单操作
     *
     * @param userId
     * @param secproductId
     * @return
     */
    @Transactional
    public String submitSec(Integer userId, Integer secproductId) {
        //
        SecProduct secProduct = secProductMapper.selectByPrimaryKey(secproductId);
        if (secProduct == null)
            return ConfigUtil.SEC_ERROR;
        // 验证地址
        List<Address> list = addressMapper.selectAByUserID(userId);
        if (list.size() == 0) {
            return ConfigUtil.SEC_ADDRESSNULL;
        }
        // 更新库存 销量
        int res = secProductMapper.updateStoreNumberAndSaleAmountById(secProduct.getSecproductId());
        if (res < 1) {// 没有更新成功 抛出异常 让事物回滚
            throw new RuntimeException(ConfigUtil.SEC_SALEALL);
        }
        // 2 下订单 生成订单项
        Order order = new Order();
        order.setOrderWay(ConfigUtil.ORDER_WAYTOPAY);
        order.setOrderTime(new Date());
        order.setUserId(userId);
        // 插入默认地址

        order.setAddress(list.get(0));
        // 插入订单
        orderMapper.insert(order);
        // 生成订单项
        OrderItem orderItem = new OrderItem();
        orderItem.setItemPrice(secProduct.getSecPrice());
        orderItem.setProduct(secProduct.getProduct());
        orderItem.setProductAmount(1);
        orderItem.setOrderId(order.getOrderId());
        orderItemMapper.insert(orderItem);
        // 3 生成秒杀订单
        SecOrder secOrder = new SecOrder();
        secOrder.setSecproductId(secproductId);
        secOrder.setUserId(userId);
        secOrderMapper.insert(secOrder);
        return ConfigUtil.SEC_SUCCESS;
    }

    public String getSecResult(Integer userId, Integer secproductId) {
        SecOrder secOrder = secOrderMapper.selectByuserIdAndsecProdectId(userId, secproductId);
        if (secOrder != null) {//订单已经生成
            return ConfigUtil.SEC_SUCCESS;
        } else if (getUserSecStatu(userId, secproductId)) {//已经卖完了
            return ConfigUtil.SEC_SALEALL;
        }
        //继续轮询
        return ConfigUtil.SEC_WAIT;
    }

    /**
     * 验证订单是否有效(redis版本)
     *
     * @param userId
     * @param secproductId
     * @return
     */
    private String validSec(Integer userId, Integer secproductId) {
        String returnStr = null;
        // 验证库存
        Long res = redisService.decr(ProductKey.getProductStockKey(), "" + secproductId);
        if (res < 0) {
            return ConfigUtil.SEC_SALEALL;
        }
        if (secProductMapper.selectStoreNumberById(secproductId) < 1) {
            setUserSecStatu(userId,secproductId);
            return ConfigUtil.SEC_SALEALL;// 商品已经售空
        }
        // 验证用户是否重复下单
        SecOrder secorder = secOrderMapper.selectByuserIdAndsecProdectId(userId, secproductId);
        if (secorder != null) {
            return ConfigUtil.SEC_REPEAT;
        }
        return ConfigUtil.SEC_SUCCESS;
    }
    /**
     * 新建秒杀地址
     */
    @Override
    public String createSecPath(Integer userId, Integer secproductId) {
        String value= UUIDUtil.uuid();
        redisService.set(ProductKey.getSecPathKey(), userId+"-"+secproductId, value);
        System.out.println(value+"value");
        return value;
    }
    /**
     * 验证路径是否正确
     */
    @Override
    public boolean checkPath(Integer userId, Integer secproductId, String path) {
        String oldPath=redisService.get(ProductKey.getSecPathKey(),userId+"-"+secproductId , String.class);
        System.out.println(oldPath+"oldPath"+path);
        if(oldPath==null ||!oldPath.equals(path)) return false;
        return true;
    }
    /**
     * 设置用户秒杀状态
     */
    private void setUserSecStatu(Integer userId, Integer secproductId) {
        redisService.set(UserKey.getUserSecKey(), userId + "-" + secproductId, 1);
    }

    private Boolean getUserSecStatu(Integer userId, Integer secproductId) {
        return redisService.exist(UserKey.getUserSecKey(), userId + "-" + secproductId);
    }

//@Transactional
//	@Override
//	public String toSec(Integer userId, Integer secproductId) {
//		String returnStr = validSec(userId, secproductId);
//		//验证失败
//		if(returnStr!=ConfigUtil.SEC_SUCCESS) {
//			return returnStr;
//		}
//		System.out.println(returnStr+"returnStr");
//		return  submitSec(userId, secproductId);
//	}
//  /**
//   * 下单操作
//   * @param userId
//   * @param secproductId
//   * @return
//   */
//private String submitSec(Integer userId, Integer secproductId) {
//	  //1 减少库存
//	 SecProduct secProduct=secProductMapper.selectByPrimaryKey(secproductId);
//	 if(secProduct==null) return ConfigUtil.SEC_ERROR;
//	 //验证地址
//	 List<Address> list=addressMapper.selectAByUserID(userId);
//		if(list.size()==0) {
//			return ConfigUtil.SEC_ADDRESSNULL;
//		}
//	  //更新库存 销量
//	 int res=secProductMapper.updateStoreNumberAndSaleAmountById(secProduct.getSecproductId());
//	  if(res<1) {//没有更新成功 抛出异常 让事物回滚
//		  throw new RuntimeException(ConfigUtil.SEC_SALEALL);
//	  }
//	 //2 下订单 生成订单项
//	   Order order=new Order();
//		order.setOrderWay(ConfigUtil.ORDER_WAYTOPAY);
//		order.setOrderTime(new Date());
//		order.setUserId(userId);
//		//插入默认地址
//
//		order.setAddress(list.get(0));
//		// 插入订单
//		orderMapper.insert(order);
//		//生成订单项
//		OrderItem orderItem=new OrderItem();
//		orderItem.setItemPrice(secProduct.getSecPrice());
//		orderItem.setProduct(secProduct.getProduct());
//		orderItem.setProductAmount(1);
//		orderItem.setOrderId(order.getOrderId());
//		orderItemMapper.insert(orderItem);
//	   //3 生成秒杀订单
//		SecOrder secOrder=new SecOrder();
//		secOrder.setSecproductId(secproductId);
//		secOrder.setUserId(userId);
//		secOrderMapper.insert(secOrder);
//	return ConfigUtil.SEC_SUCCESS;
// }
    /**
     * 验证订单是否有效
     *
     * @param userId
     * @param secproductId
     * @return
     */
    public String validSecBySql(Integer userId, Integer secproductId) {
        String returnStr = null;
        // 验证用户是否重复下单
        SecOrder secorder = secOrderMapper.selectByuserIdAndsecProdectId(userId, secproductId);
        if (secorder != null) {
            return ConfigUtil.SEC_REPEAT;
        }
        // 验证库存
        if (secProductMapper.selectStoreNumberById(secproductId) < 1) {
            return ConfigUtil.SEC_SALEALL;// 商品已经售空
        }
        return ConfigUtil.SEC_SUCCESS;
    }


}

