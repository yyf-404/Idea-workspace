package com.yyf.mallcache.bean;

import java.util.Date;
import java.util.List;

public class Order {

	private Integer orderId;

	private Integer userId;

	private Date orderTime;

	private String orderWay;

	private List<OrderItem> items;

	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderWay() {
		return orderWay;
	}

	public void setOrderWay(String orderWay) {
		this.orderWay = orderWay == null ? null : orderWay.trim();
	}
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(Integer orderId, Integer userId, Date orderTime, String orderWay, List<OrderItem> items,
			Address address) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.orderTime = orderTime;
		this.orderWay = orderWay;
		this.items = items;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", orderTime=" + orderTime + ", orderWay=" + orderWay
				+ ", items=" + items + ", address=" + address + "]";
	}
	

}