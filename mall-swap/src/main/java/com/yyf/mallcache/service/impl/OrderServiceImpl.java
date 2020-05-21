package com.yyf.mallcache.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yyf.mallcache.bean.*;
import com.yyf.mallcache.mapper.*;
import com.yyf.mallcache.service.OrderService;
import com.yyf.mallcache.service.ProductService;
import com.yyf.mallcache.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ShoppingItemMapper shoppingItemMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private ProductService productService;

	@Transactional
	@Override
	public String submitOrder(Integer[] productIds, User user, Integer addressId, Integer accountNumber,
							  String loginId) {
		// 验证
		String returnFlag = confirmPay(user, accountNumber, loginId);
		if (!returnFlag.equals(ConfigUtil.PAY_SUCCESS))
			return returnFlag;
		Order order = new Order();
		order.setOrderWay(ConfigUtil.DEFAULT_ORDERWAY);
		order.setOrderTime(new Date());
		order.setUserId(user.getUserId());
		order.setAddress(new Address(addressId));
		// 插入订单
		orderMapper.insert(order);
		// 生成订单项
		List<OrderItem> orderItems = new ArrayList();
		BigDecimal totalPrice = new BigDecimal(0);
		for (int i = 0; i < productIds.length; i++) {
			ShoppingItem shoppingItem = shoppingItemMapper.getItemByProductIdAndUserId(productIds[i], user.getUserId());
			System.out.println("ShoppingItem" + shoppingItem);
			if (shoppingItem != null && shoppingItem.getItemId() != null) {
				OrderItem orderItem = new OrderItem();
				orderItem.setProduct(shoppingItem.getProduct());
				orderItem.setProductAmount(shoppingItem.getProductAmount());
				// 验证库存
				if (shoppingItem.getProduct().getProductStorenumber() < shoppingItem.getProductAmount())
					throw new RuntimeException(ConfigUtil.PAY_PRODUCTAMOUNTFAIL);
				// 更新库存 销量
				productService.updateProduct(shoppingItem.getProduct(), shoppingItem);
				// 统计总价
				orderItem.setItemPrice(shoppingItem.getItemPrice());
				totalPrice = totalPrice.add(shoppingItem.getItemPrice());
				orderItem.setOrderId(order.getOrderId());
				shoppingItem.setUserId(user.getUserId());
				// 删除已购买的购物项
				shoppingItemMapper.deleteByUserIdAndProductIdy(productIds[i], user.getUserId());
				orderItems.add(orderItem);
			}
		}
		// 验证账户于余额
		BigDecimal accountBalance=accountMapper.selectAccountBalanceByUserId(user.getUserId());
		if (totalPrice.compareTo(accountBalance)==1) {
			throw new RuntimeException(ConfigUtil.PAY_ACCOUNTNBALANCEFAIL.toString());
		}
		// 要账号扣费的操作
		accountMapper.updateAccountBalance(accountBalance.subtract(totalPrice), user.getUserId());
		System.out.println("orderItems" + orderItems);
		// 插入订单项
		orderItemMapper.batchinsert(orderItems);
		return ConfigUtil.PAY_SUCCESS;
	}

	@Override
	public List<Order> getUserOrderById(Integer userId) {
		return orderMapper.selectByUserId(userId);
	}

	/**
	 * 确认支付密码 登陆id 的正确性
	 *
	 * @return
	 */
	private String confirmPay(User user, Integer accountNumber, String loginId) {
		// 验证用户id
		if (user.getUserId() == null)
			return ConfigUtil.PAY_PLEASE;
		// 验证登陆密码
		if (!user.getLoginId().equals(loginId))
			return ConfigUtil.PAY_LOGINIDFAIL;
		// 得到数据库中的支付密码
		Integer dbaccountNumber = accountMapper.selectAccountNumberByUserId(user.getUserId());
		if (!dbaccountNumber.equals(accountNumber))
			return ConfigUtil.PAY_ACCOUNTNUMBERTFAIL;

		return ConfigUtil.PAY_SUCCESS;
	}



}