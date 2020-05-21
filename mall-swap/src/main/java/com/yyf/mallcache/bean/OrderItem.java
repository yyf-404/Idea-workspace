package com.yyf.mallcache.bean;

import java.math.BigDecimal;

public class OrderItem {

    private Integer itemId;

    private Integer orderId;

    private Product product;

    private Integer productAmount;

    private BigDecimal itemPrice;


    public Integer getItemId() {
        return itemId;
    }


    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }


    public Integer getOrderId() {
        return orderId;
    }

 
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    public Product getProduct() {
        return product;
    }


    public void setProduct(Product product) {
        this.product = product;
    }


    public Integer getProductAmount() {
        return productAmount;
    }


    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

 
    public BigDecimal getItemPrice() {
        return itemPrice;
    }

 
    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }
    public OrderItem() {
		// TODO Auto-generated constructor stub
	}


	public OrderItem(Integer itemId, Integer orderId, Product product, Integer productAmount, BigDecimal itemPrice) {
		super();
		this.itemId = itemId;
		this.orderId = orderId;
		this.product = product;
		this.productAmount = productAmount;
		this.itemPrice = itemPrice;
	}


	@Override
	public String toString() {
		return "OrderItem [itemId=" + itemId + ", orderId=" + orderId + ", product=" + product + ", productAmount="
				+ productAmount + ", itemPrice=" + itemPrice + "]";
	}
    
}