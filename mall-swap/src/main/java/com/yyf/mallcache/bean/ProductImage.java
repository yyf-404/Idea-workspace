package com.yyf.mallcache.bean;
/*
 商品图片连接表
 */
public class ProductImage {
    private int productId;
    private int imageId;
    private String imageKind;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageKind() {
        return imageKind;
    }

    public void setImageKind(String imageKind) {
        this.imageKind = imageKind;
    }

    public ProductImage(int productId, int imageId, String imageKind) {
        this.productId = productId;
        this.imageId = imageId;
        this.imageKind = imageKind;
    }

    public ProductImage() {
    }
}
