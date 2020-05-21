package com.yyf.mallcache.mapper;


import com.yyf.mallcache.bean.ProductInfo;
import com.yyf.mallcache.bean.SecProductInfo;

public interface ProductInfoMapper {
	ProductInfo getProductInfoById(Integer product_id);

	SecProductInfo getSecProductInfoById(Integer product_id);
}
