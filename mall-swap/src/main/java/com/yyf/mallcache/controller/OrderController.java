package com.yyf.mallcache.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yyf.mallcache.bean.Order;
import com.yyf.mallcache.bean.User;
import com.yyf.mallcache.redis.UserKey;
import com.yyf.mallcache.service.OrderService;
import com.yyf.mallcache.service.RedisService;
import com.yyf.mallcache.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private RedisService redisService;

	@ResponseBody
	@RequestMapping("/submitOrder")
	public String submitOrder(@RequestParam("productIds[]") Integer[] productIds,
							  @RequestParam("loginId") String loginId, @RequestParam("addressId") Integer addressId,
							  @RequestParam("accountNumber") Integer accountNumber, HttpServletRequest request,
							  @CookieValue(value= ConfigUtil.COOKIE_NAME_TOKEN,required=false)String cookieToken) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ConfigUtil.USERSTR);
		user=redisService.get(UserKey.getUserTokenKey(), cookieToken, User.class);
		String returnFlag=ConfigUtil.PAY_FAIL;
		try {
			returnFlag=orderService.submitOrder(productIds, user, addressId, accountNumber, loginId);
		}catch (Exception e) {
			return e.getMessage();
		}
		return returnFlag;
	}

	@ResponseBody
	@RequestMapping("/getUserOrder")
	public List<Order> getUserOrder(HttpServletRequest request,
									@CookieValue(value=ConfigUtil.COOKIE_NAME_TOKEN,required=false)String cookieToken) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ConfigUtil.USERSTR);
		user=redisService.get(UserKey.getUserTokenKey(), cookieToken, User.class);
		if (user != null) {
			return orderService.getUserOrderById(user.getUserId());
		}
		return null;
	}

}
