package com.yyf.mallcache.bean;

import java.io.Serializable;
import java.math.BigDecimal;


public class Product  {
	
	private Integer productId;

	private String productName;

	private String productDetail;

	private BigDecimal productPrice;

	private String productTaste;

	private Integer productStorenumber;

	private Integer productSalesamount;
	private Image image;

	public Product() {
	}

	public Product(Integer productId,String productName, String productDetail, BigDecimal productPrice, Integer productStorenumber, Integer productSalesamount) {
		this.productId = productId;
		this.productName = productName;
		this.productDetail = productDetail;
		this.productPrice = productPrice;
		this.productStorenumber = productStorenumber;
		this.productSalesamount = productSalesamount;
	}

	public Product(Product p) {
		this.productId=p.productId;
		this.productName=p.productName;
	}
	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductDetail() {
		return productDetail;
	}


	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}


	public BigDecimal getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}


	public String getProductTaste() {
		return productTaste;
	}


	public void setProductTaste(String productTaste) {
		this.productTaste = productTaste;
	}


	public Integer getProductStorenumber() {
		return productStorenumber;
	}


	public void setProductStorenumber(Integer productStorenumber) {
		this.productStorenumber = productStorenumber;
	}


	public Integer getProductSalesamount() {
		return productSalesamount;
	}


	public void setProductSalesamount(Integer productSalesamount) {
		this.productSalesamount = productSalesamount;
	}


	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}


	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDetail=" + productDetail
				+ ", productPrice=" + productPrice + ", productTaste=" + productTaste + ", productStorenumber="
				+ productStorenumber + ", productSalesamount=" + productSalesamount + ", image=" + image + "]";
	}
	
}