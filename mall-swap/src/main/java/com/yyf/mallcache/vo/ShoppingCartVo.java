package com.yyf.mallcache.vo;

import com.yyf.mallcache.bean.ShoppingItem;

import java.math.BigDecimal;
import java.util.List;


public class ShoppingCartVo {
 

    private Integer userId;
    
    private BigDecimal totalPrice;
    
    private List<ShoppingItem> shoppingItems;
    
    public List<ShoppingItem> getShoppingItems() {
		return shoppingItems;
	}
    /**.
                *   ������shoppingItemsֵ��ͬʱ����totalPrice
     * @param shoppingItems
     */
    public void setShoppingItems(List<ShoppingItem> shoppingItems) {
		this.shoppingItems = shoppingItems;
		BigDecimal count=new BigDecimal(0);
		if(shoppingItems!=null) {
			for(ShoppingItem item:shoppingItems) {
				count=count.add(item.getItemPrice());
			}
			totalPrice=count;
		}
	}


 
    public Integer getUserId() {
        return userId;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    /**
     * �޷������ܼ�  �ɼ���ó�
     * 
     */
    public void setTotalPrice(BigDecimal totalPrice) {
    }
}