package com.yyf.mallcache.util;


import com.alibaba.fastjson.JSON;

public class FastJsonUtil {
	public static <T> T stringToBean(String str,Class<T> clazz) {
		if(str==null||str.length()<1||clazz==null) {
			return null;
		}
		if(clazz==int.class||clazz==Integer.class) {
			return (T) Integer.valueOf(str);
		}else if(clazz==long.class||clazz==Long.class) {
			return (T) Long.valueOf(str);
		}else if(clazz==String.class) {
			return (T) str;
		}
		return JSON.toJavaObject(JSON.parseObject(str), clazz);

	}

	public static  <T> String beanToString(T bean) {
		if(bean==null) {
			return null;
		}
		Class clazz=bean.getClass();
		if(clazz==int.class||clazz==Integer.class) {
			return ""+bean;
		}else if(clazz==long.class||clazz==Long.class) {
			return ""+bean;
		}else if(clazz==String.class) {
			return (String)bean;
		}
		return JSON.toJSONString(bean);

	}

}
