package com.yyf.mallcache.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.yyf.mallcache.bean.*;
import com.yyf.mallcache.redis.UserKey;
import com.yyf.mallcache.service.ImageService;
import com.yyf.mallcache.service.ProductService;
import com.yyf.mallcache.service.RedisService;
import com.yyf.mallcache.util.ConfigUtil;
import com.yyf.mallcache.util.LoggerUtil;
import com.yyf.mallcache.vo.ProductPO;
import com.yyf.mallcache.vo.ProductPageVO;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ProductConstoller {
	@Autowired
	private ProductService productService;
	@Autowired
	private ImageService imageService;
	@Autowired
	RedisService redisService;
	@ResponseBody
	@RequestMapping("/productList")
	public ProductPageVO productList(@Param("productKind") String productKind, @Param("orderWay") Integer orderWay,
									 @Param("pageNo") Integer pageNo, @Param("productName") String productName) throws IOException {
		return productService.getProductPageVO(new ProductPO(productKind, orderWay, pageNo, productName));
	}

	@ResponseBody
	@RequestMapping("/product")
	public ProductInfo product(@Param("productId") Integer productId) throws JsonProcessingException {
		ProductInfo productInfo = productService.getProductInfo(productId);
		return productInfo;
	}

	@ResponseBody
	@RequestMapping("/secProductList")
	public List<SecProduct> secProductList() throws IOException {

		return productService.getSecProductList();
	}

	@ResponseBody
	@RequestMapping("/secProduct")
	public SecProductInfo secProduct(@Param("productId") Integer productId) throws JsonProcessingException {
		return productService.getSecProductInfo(productId);
	}

	@ResponseBody
	@RequestMapping("/addProduct")
	public String addProduct(@RequestParam("productName") String productName, @RequestParam("productDetail") String productDetail,
							 @RequestParam("productPrice") BigDecimal productPrice, @RequestParam("productStorenumber") Integer productStorenumber,
							 @RequestParam("searchImage") MultipartFile searchImage, @RequestParam("detailImage") MultipartFile detailImage,
							 @CookieValue(value=ConfigUtil.COOKIE_NAME_TOKEN,required=false)String cookieToken) throws IOException {
		LoggerUtil.getLogger(this.getClass()).info(productName + "productDetail= " + productDetail
				+ " productStorenumber=" + productStorenumber + "productPrice=" + productPrice + "file=" + searchImage.getName() + " " + detailImage.getName()+"cookieToken="+cookieToken);
		if(searchImage==null||searchImage==null) return ConfigUtil.ADD_FAIL;
		User user=redisService.get(UserKey.getUserTokenKey(), cookieToken, User.class);
		if(user==null||user.getUserId()==null) return ConfigUtil.ADD_FAIL;
		boolean flag=productService.addProduct(user.getUserId(),productName, productDetail, productPrice, productStorenumber, searchImage.getInputStream(), searchImage.getInputStream());
		if(flag) return ConfigUtil.ADD_SUCCESS;
		return ConfigUtil.ADD_FAIL;
	}
	/*
	 获得用户上架商品
	 */
	@ResponseBody
	@RequestMapping("/getUserProducts")
	public List<Product> getUserProducts(@CookieValue(value=ConfigUtil.COOKIE_NAME_TOKEN,required=false)String cookieToken) throws IOException {
		LoggerUtil.getLogger(this.getClass()).info("cookieToken="+cookieToken);
		User user=redisService.get(UserKey.getUserTokenKey(), cookieToken, User.class);
		if(user==null||user.getUserId()==null) return null;
		return productService.getUserProducts(user.getUserId());
	}

		/*
	    获得商品对应评论
	    */
	@ResponseBody
	@RequestMapping("/getComments")
	public List<Comment> getComments(@RequestParam("productId") Integer productId) throws IOException {
		List<Comment> list=productService.getComments(productId);
		LoggerUtil.getLogger(this.getClass()).info(Arrays.asList(list).toString());
      return  list;
	}
	/*
提交评论
    */
	@ResponseBody
	@RequestMapping("/submitComments")
	public Integer submitComments(@RequestParam("productId") Integer productId,@RequestParam("userId")
			Integer userId,@RequestParam("words") String words) throws IOException {
		if(userId==null) return null;
				LoggerUtil.getLogger(this.getClass()).info("productId="+productId+" userId="+userId+" words"+words);
		return  productService.submitComments(productId,userId,words);
	}
}
