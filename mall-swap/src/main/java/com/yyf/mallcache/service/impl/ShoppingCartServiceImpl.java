package com.yyf.mallcache.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.yyf.mallcache.bean.Product;
import com.yyf.mallcache.bean.ShoppingItem;
import com.yyf.mallcache.mapper.ProductMapper;
import com.yyf.mallcache.mapper.ShoppingItemMapper;
import com.yyf.mallcache.service.ShoppingCartService;
import com.yyf.mallcache.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yyf.mallcache.vo.ShoppingCartVo;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	@Autowired
	ProductMapper productMapper;
	@Autowired
	ShoppingItemMapper shoppingItemMapper ;
	@Override
	public void insertItem(Integer productId, Integer productAmount, Integer userId) {

		BigDecimal itemPrice = getItemPrice(productId, productAmount);
		if (itemPrice != null) {
			Integer len = shoppingItemMapper.selectByUserIdAndProductId(productId, userId);
			if (len != null && len > 0) {
				upadateItem(productId, productAmount + len, userId);
				return;
			}
			ShoppingItem item = new ShoppingItem(productId, productAmount,
					itemPrice, userId);
			LoggerUtil.getLogger(this.getClass()).info(item.toString());
			shoppingItemMapper.insert(item);
		}
	}
	@Override
	public BigDecimal upadateItem(Integer productId, Integer productAmount, Integer userId) {
		BigDecimal itemPrice=getItemPrice(productId, productAmount);
		LoggerUtil.getLogger(this.getClass()).info(itemPrice+"itemPrice");
		if(itemPrice!=null ) {
		ShoppingItem item = new ShoppingItem(productId, productAmount,
				itemPrice, userId);
		System.out.println(item+"item");
		shoppingItemMapper.updateByUserIdAndProductId(item);
		return itemPrice;
		}
		return new BigDecimal(0);
	}
	/**
	 * ɾ��������
	 */
	@Override
	public Integer deleteItem(Integer productId, Integer userId) {
		return shoppingItemMapper.deleteByUserIdAndProductIdy(productId, userId);
	}
	/**
	 * ͨ���û�idɾ��������Ʒ��
	 */
	@Override
	public Integer deleteAll(Integer userId) {
		return shoppingItemMapper.deleteByUserId(userId);
	}
	/**
	 * �õ�ʵ�ֹ��ﳵ����
	 */
	public ShoppingCartVo getShoppingCart(Integer userId) {
		List<ShoppingItem> items=null;
		ShoppingCartVo shoppingCartVo=null;
		if(userId!=null)
			items =shoppingItemMapper.selectByUserId(userId);
		if(items!=null&&items.size()>0) {
			shoppingCartVo=new ShoppingCartVo();
			shoppingCartVo.setUserId(userId);
			// ��items������� shoppingCartVo��TotalPrice���Զ�����ֵ
			shoppingCartVo.setShoppingItems(items);
			
		}
		return shoppingCartVo;
	}
	
/**
 * 计算商品价格（不能通过前端计算 容易被篡改）
 * 可加上折扣
 * @param productId
 * @param productAmount
 * @return
 */
	private BigDecimal getItemPrice(Integer productId, Integer productAmount) {
		Product product=productMapper.selectByPrimaryKey(productId);

		if(product!=null&&product.getProductPrice()!=null &&productAmount>0) {
			return product.getProductPrice().multiply(new BigDecimal(productAmount));
		}
		return null;
	}



}
