package com.yyf.mallcache.bean;

import com.yyf.mallcache.util.FastJsonUtil;

import java.math.BigDecimal;
import java.util.Date;
//��ɱ��Ʒ ��װ���װ��һ��product
public class SecProduct {
 
    private Integer secproductId;


    private Product product;

 
    private BigDecimal secPrice;


    private Integer secStorenumber;

  
    private Integer secSalesamount;

 
    private Date startDate;

  
    private Date endDate;

 
    public Integer getSecproductId() {
        return secproductId;
    }

 
    public void setSecproductId(Integer secproductId) {
        this.secproductId = secproductId;
    }

  
    public Product getProduct() {
        return product;
    }

  
    public void setProduct(Product product) {
        this.product = product;
    }

 
    public BigDecimal getSecPrice() {
        return secPrice;
    }

 
    public void setSecPrice(BigDecimal secPrice) {
        this.secPrice = secPrice;
    }

 
    public Integer getSecStorenumber() {
        return secStorenumber;
    }

 
    public void setSecStorenumber(Integer secStorenumber) {
        this.secStorenumber = secStorenumber;
    }

  
    public Integer getSecSalesamount() {
        return secSalesamount;
    }

  
    public void setSecSalesamount(Integer secSalesamount) {
        this.secSalesamount = secSalesamount;
    }

  
    public Date getStartDate() {
        return startDate;
    }

  
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

   
    public Date getEndDate() {
        return endDate;
    }

   
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    //�����ڲ�product������
	public Integer getProductId() {
		return product.getProductId();
	}


	public void setProductId(Integer productId) {
		product.setProductId(productId);
	}


	public String getProductName() {
		return product.getProductName();
	}


	public void setProductName(String productName) {
		product.setProductName(productName);
	}


	public String getProductDetail() {
		return product.getProductDetail();
	}


	public void setProductDetail(String productDetail) {
		product.setProductDetail(productDetail);	
		}


	public BigDecimal getProductPrice() {
		return product.getProductPrice() ;
	}


	public void setProductPrice(BigDecimal productPrice) {
		product.setProductPrice(productPrice);
	}


	public String getProductTaste() {
		return product.getProductTaste() ;
	}


	public void setProductTaste(String productTaste) {
		product.setProductTaste(productTaste);
	}


	public Integer getProductStorenumber() {
		return product.getProductStorenumber() ;
	}


	public void setProductStorenumber(Integer productStorenumber) {
		product.setProductStorenumber(productStorenumber);
	}


	public Integer getProductSalesamount() {
		return product.getProductSalesamount();
	}


	public void setProductSalesamount(Integer productSalesamount) {
		product.setProductSalesamount(productSalesamount);
	}

    @Override
    public String toString() {
        return "SecProduct{" +
                "secproductId=" + secproductId +
                ", product=" + product +
                ", secPrice=" + secPrice +
                ", secStorenumber=" + secStorenumber +
                ", secSalesamount=" + secSalesamount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}