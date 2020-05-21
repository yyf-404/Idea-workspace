package com.yyf.mallcache.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yyf.mallcache.bean.User;
import com.yyf.mallcache.service.ShoppingCartService;
import com.yyf.mallcache.util.ConfigUtil;
import com.yyf.mallcache.util.LoggerUtil;
import com.yyf.mallcache.vo.ShoppingCartVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ShoppingCartController {
	@Autowired
	ShoppingCartService shoppingCartService;

	/**
	 * 添加商品到购物车
	 */
	@ResponseBody
	@RequestMapping("/addToCart")
	public void addToCart(@Param("productId") Integer productId, @Param("productAmount") Integer productAmount,
						  @Param("userId") Integer userId) {
		LoggerUtil.getLogger(this.getClass()).info("productId="+productId+" productAmount="+productAmount+" userId="+userId);
		shoppingCartService.insertItem(productId, productAmount, userId);
	}

	/**
	 * 得到购物车
	 *
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/toShoppingCart")
	public ShoppingCartVo toShoppingCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ConfigUtil.USERSTR);
		if (user != null)
			return shoppingCartService.getShoppingCart(user.getUserId());
		return null;
	}

	/**
	 * 修改购物车商品数量 返回items价格
	 *
	 * @param productId
	 * @param productAmount
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateShoppingItem")
	public BigDecimal updateShoppingItem(@Param("productId") Integer productId,
										 @Param("productAmount") Integer productAmount, @Param("userId") Integer userId) {
		return shoppingCartService.upadateItem(productId, productAmount, userId);

	}

	@ResponseBody
	@RequestMapping("/deleteShoppingItem")
	public Integer deleteShoppingItem(@Param("productId") Integer productId, @Param("userId") Integer userId) {
		return shoppingCartService.deleteItem(productId, userId);
	}
	@ResponseBody
	@RequestMapping("/deleteAllItem")
	public Integer deleteAllItem( @Param("userId") Integer userId) {
		return shoppingCartService.deleteAll(userId);
	}

}
