package com.yyf.mallcache.service;

import com.yyf.mallcache.bean.Order;
import com.yyf.mallcache.bean.User;

import java.util.List;



public interface OrderService {
	String submitOrder(Integer[] productIds, User user, Integer addressId, Integer accountNumber, String loginId);
 /**
  * ����û����ж���
  */
 List<Order> getUserOrderById(Integer userId);

}
