package com.yyf.mallcache.vo;

public class ProductPO {
	private String productKind;
	private Integer orderWay;
	private Integer pageNo;
	private String productName;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductKind() {
		return productKind;
	}
	public void setProductKind(String productKind) {
		this.productKind = productKind;
	}
	public Integer getOrderWay() {
		return orderWay;
	}
	public void setOrderWay(Integer orderWay) {
		this.orderWay = orderWay;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public ProductPO(String productKind, Integer orderWay, Integer pageNo,String productName) {
		super();
		this.productKind = productKind;
		this.orderWay = orderWay;
		this.pageNo = pageNo;
		this.productName=productName;
	}
	public ProductPO() {
		
	}
	@Override
	public String toString() {
		return "ProductPO [productKind=" + productKind + ", orderWay=" + orderWay + ", pageNo=" + pageNo
				+ ", productName=" + productName + "]";
	}
	
}
