package com.yyf.mallcache.controller;

import java.util.List;

import com.yyf.mallcache.bean.Product;
import com.yyf.mallcache.bean.SecProduct;
import com.yyf.mallcache.bean.User;
import com.yyf.mallcache.redis.ProductKey;
import com.yyf.mallcache.redis.UserKey;
import com.yyf.mallcache.service.ProductService;
import com.yyf.mallcache.service.RedisService;
import com.yyf.mallcache.service.SecService;
import com.yyf.mallcache.util.ConfigUtil;
import com.yyf.mallcache.util.LoggerUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecController implements InitializingBean{
	@Autowired
	RedisService redisService;
	@Autowired
	SecService serService;
	@Autowired
	ProductService productSevice;
	/**
	 * 初始化的时候调用
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		List<SecProduct> products=productSevice.getSecProductList();
		if(products==null) {
			return;
		}

		for(SecProduct product:products) {
			LoggerUtil.getLogger(getClass()).info("redisService"+product.getProductId());
			//存储秒杀商品的数量
			redisService.set(ProductKey.getProductStockKey(), ""+product.getSecproductId(), product.getSecStorenumber());
			//存储秒杀商品
			redisService.set(ProductKey.getSecProductKey(), ""+product.getProductId(), product);
		}

	}
	@ResponseBody
	@RequestMapping("/{path}/toSec")
	public String toSec(@CookieValue(value=ConfigUtil.COOKIE_NAME_TOKEN,required=false)String cookieToken,
						@RequestParam("secproductId") Integer secproductId,
						@PathVariable("path")String path) {

		User user=redisService.get(UserKey.getUserTokenKey(), cookieToken, User.class);

		if(user==null||user.getUserId()==null) return ConfigUtil.SEC_LOGIN;
		if(serService.checkPath(user.getUserId(), secproductId, path)!=true) {
			return ConfigUtil.SEC_ILLEGAL;
		}
		try {
			return serService.toSec(user.getUserId(),secproductId);

		}catch(Exception e) {
			return e.getMessage();
		}

	}
	/**
	 * 轮询获得秒杀结果
	 * ConfigUtil.SEC_SUCCESS 秒杀成功
	 * ConfigUtil.SEC_WAIT 还在等待执行
	 * ConfigUtil.SEC_SALEALL 商         品卖完了
	 * @param cookieToken
	 * @param secproductId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSecResult")
	public String getSecResult(@CookieValue(value=ConfigUtil.COOKIE_NAME_TOKEN,required=false)String cookieToken,
							   @RequestParam("secproductId") Integer secproductId) {
		User user=redisService.get(UserKey.getUserTokenKey(), cookieToken, User.class);
		if(user==null) return ConfigUtil.SEC_LOGIN;
		try {
			if(user.getUserId()!=null) {
				return  serService.getSecResult(user.getUserId(), secproductId);

			}
		}catch(Exception e) {
			return e.getMessage();
		}
		return null;
	}
	@ResponseBody
	@RequestMapping("/getSecPath")
	public String getSecPath(@CookieValue(value= ConfigUtil.COOKIE_NAME_TOKEN,required=false)String cookieToken,
							 @RequestParam("secproductId") Integer secproductId) {
		User user=redisService.get(UserKey.getUserTokenKey(), cookieToken, User.class);
		if(user==null) return ConfigUtil.SEC_LOGIN;
		try {
			if(user.getUserId()!=null) {
				return serService.createSecPath(user.getUserId(), secproductId);
			}
		}catch(Exception e) {
			return e.getMessage();
		}
		return null;
	}

}
