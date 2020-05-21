package com.yyf.mallcache.service;

import com.yyf.mallcache.vo.ShoppingCartVo;

import java.math.BigDecimal;

public interface ShoppingCartService {
	void insertItem(Integer productId, Integer productAmount, Integer userId);

	ShoppingCartVo getShoppingCart(Integer userId);

	BigDecimal upadateItem(Integer productId, Integer productAmount, Integer userId);
	
	Integer deleteItem(Integer productId, Integer userId);
	
	Integer deleteAll(Integer userId);
	
}
