package com.yyf.mallcache.bean;

import java.util.List;

/**
 * 秒杀商品详情
 *
 * @author Yao98
 *
 */
public class SecProductInfo {
	private SecProduct product;
	private List<Image> detailImage;
	private List<Image> loupeBigImage;
	private List<Image> loupeMidImage;
	private List<Image> loupeSmallImage;
	private String productBrand;
	private Image shoppingCartImage;
	private List<String> productTasteList;
	// 秒杀状态
	private String secWay;
	// 秒杀倒计时
	private Long remailSeconds;

	public List<String> getProductTasteList() {
		return productTasteList;
	}

	public void setProductTasteList(List<String> productTasteList) {
		this.productTasteList = productTasteList;
	}

	public SecProduct getProduct() {
		return product;
	}

	public void setProduct(SecProduct product) {
		this.product = product;
	}

	public List<Image> getDetailImage() {
		return detailImage;
	}

	public void setDetailImage(List<Image> detailImage) {
		this.detailImage = detailImage;
	}

	public List<Image> getLoupeBigImage() {
		return loupeBigImage;
	}

	public void setLoupeBigImage(List<Image> loupeBigImage) {
		this.loupeBigImage = loupeBigImage;
	}

	public List<Image> getLoupeMidImage() {
		return loupeMidImage;
	}

	public void setLoupeMidImage(List<Image> loupeMidImage) {
		this.loupeMidImage = loupeMidImage;
	}

	public List<Image> getLoupeSmallImage() {
		return loupeSmallImage;
	}

	public void setLoupeSmallImage(List<Image> loupeSmallImage) {
		this.loupeSmallImage = loupeSmallImage;
	}

	public Image getShoppingCartImage() {
		return shoppingCartImage;
	}

	public void setShoppingCartImage(Image shoppingCartImage) {
		this.shoppingCartImage = shoppingCartImage;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getSecWay() {
		return secWay;
	}

	public void setSecWay(String secWay) {
		this.secWay = secWay;
	}

	public Long getRemailSeconds() {
		return remailSeconds;
	}

	public void setRemailSeconds(Long remailSeconds) {
		this.remailSeconds = remailSeconds;
	}

	public SecProductInfo() {
		// TODO Auto-generated constructor stub
	}

	public SecProductInfo(SecProduct product, List<Image> detailImage, List<Image> loupeBigImage,
						  List<Image> loupeSmallImage, Image shoppingCartImage, String productBrand) {
		super();
		this.product = product;
		this.detailImage = detailImage;
		this.loupeBigImage = loupeBigImage;
		this.loupeSmallImage = loupeSmallImage;
		this.shoppingCartImage = shoppingCartImage;
		this.productBrand = productBrand;
	}

	@Override
	public String toString() {
		return "ProductInfo [product=" + product + ", detailImage=" + detailImage + ", loupeBigImage=" + loupeBigImage
				+ ", loupeSmallImage=" + loupeSmallImage + ", shoppingCartImage=" + shoppingCartImage
				+ ", productBrand=" + productBrand + "]";
	}

}
