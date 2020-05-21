package com.yyf.mallcache.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
	@Override



	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("/home/search.html");
		registry.addViewController("/home").setViewName("/home/search.html");
		registry.addViewController("/search").setViewName("/home/search.html");
		registry.addViewController("/login").setViewName("/home/login.html");
		registry.addViewController("/register").setViewName("/home/register.html");
		registry.addViewController("/loginSuccess").setViewName("/home/loginSuccess.html");
		registry.addViewController("/registerSuccess").setViewName("/home/registerSuccess.html");
		registry.addViewController("/paySuccess").setViewName("/home/paySuccess.html");
		registry.addViewController("/introduction").setViewName("/home/introduction.html");
		registry.addViewController("/secintroduction").setViewName("/home/secintroduction.html");
		registry.addViewController("/shopcart").setViewName("/home/shopcart.html");
		registry.addViewController("/pay").setViewName("/home/pay.html");
		registry.addViewController("/person/order").setViewName("/home/person/order.html");
		registry.addViewController("/person/index").setViewName("/home/person/index.html");
		registry.addViewController("/person/information").setViewName("/home/person/information.html");
		registry.addViewController("/person/bill").setViewName("/home/person/bill.html");
		registry.addViewController("/person/address").setViewName("/home/person/address.html");
		registry.addViewController("/person/collection").setViewName("/home/person/collection.html");
		registry.addViewController("/person/bonus").setViewName("/home/person/bonus.html");
		registry.addViewController("/person/coupon").setViewName("/home/person/coupon.html");
		registry.addViewController("/person/safety").setViewName("/home/person/safety.html");
		registry.addViewController("/person/setpay").setViewName("/home/person/setpay.html");
		registry.addViewController("/person/password").setViewName("/home/person/password.html");
		registry.addViewController("/person/imageUpload").setViewName("/home/person/imageUpload.html");
		registry.addViewController("/person/productsManagement").setViewName("/home/person/productsManagement.html");
		registry.addViewController("/person/addProduct").setViewName("/home/person/addProduct.html");

	}
}
