package com.yyf.mallcache.bean;

public class Address {

	private Integer addressId;

	private Integer userId;

	private String addressProvince;

	private String addressCity;

	private String addressDetail;

	private String addressName;

	private String addressPhone;

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddressProvince() {
		return addressProvince;
	}

	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince == null ? null : addressProvince.trim();
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity == null ? null : addressCity.trim();
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail == null ? null : addressDetail.trim();
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName == null ? null : addressName.trim();
	}

	public String getAddressPhone() {
		return addressPhone;
	}

	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone == null ? null : addressPhone.trim();
	}

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(Integer addressId, Integer userId, String addressProvince, String addressCity, String addressDetail,
			String addressName, String addressPhone) {
		super();
		this.addressId = addressId;
		this.userId = userId;
		this.addressProvince = addressProvince;
		this.addressCity = addressCity;
		this.addressDetail = addressDetail;
		this.addressName = addressName;
		this.addressPhone = addressPhone;
	}

	public Address(Integer addressId) {
		this.addressId = addressId;
	}
}