package com.yyf.mallcache.bean;

import java.math.BigDecimal;

public class ShoppingItem {

    private Integer itemId;


    private Integer productId;


    private Integer productAmount;


    private BigDecimal itemPrice;

    private Integer userId;
    
    private Product product;
    public Product getProduct() {
		return product;
	}
    public void setProduct(Product product) {
		this.product = product;
	}
    public Integer getItemId() {
        return itemId;
    }


    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

 
    public Integer getProductId() {
        return productId;
    }


    public void setProductId(Integer productId) {
        this.productId = productId;
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


    public Integer getUserId() {
        return userId;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public ShoppingItem() {
		// TODO Auto-generated constructor stub
	}


	public ShoppingItem( Integer productId, Integer productAmount, BigDecimal itemPrice,
			Integer userId) {
		super();
		this.itemId = itemId;
		this.productId = productId;
		this.productAmount = productAmount;
		this.itemPrice = itemPrice;
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "ShoppingItem [itemId=" + itemId + ", productId=" + productId + ",product="+ product +", productAmount=" + productAmount
				+ ", itemPrice=" + itemPrice + ", userId=" + userId + "]";
	}
	
    
}