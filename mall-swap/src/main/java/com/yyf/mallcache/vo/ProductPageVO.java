package com.yyf.mallcache.vo;


import com.yyf.mallcache.bean.Product;
import com.yyf.mallcache.util.ConfigUtil;

import java.util.List;

public class ProductPageVO {
private List<Product> productList;
private int pageSize;
private Integer productTotalCount;
private int pageNo;

public int getPageNo() {
	return pageNo;
}
public void setPageNo(int pageNo) {
	if(pageNo<1) pageNo=1;
	this.pageNo = pageNo;
}
public List<Product> getProductList() {
	return productList;
}
public void setProductList(List<Product> productList) {
	this.productList = productList;
}
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = ConfigUtil.PAGE_SIZE;
}
public Integer getProductTotalCount() {
	return productTotalCount;
}
public void setProductTotalCount(Integer productTotalCount) {
	this.productTotalCount = productTotalCount;
}
 public ProductPageVO (){
	 this.pageSize = ConfigUtil.PAGE_SIZE;
}
 
}
