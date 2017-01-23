package com.java.bean;

import java.util.ArrayList;
import java.util.List;

public class Order{
	private String orderId;
	private String customerName;
	private String orderStatus;
	private String orderType;
	private String productName;
	private int productQuantity;
	private List groceryList= new ArrayList<Product>();
	private List smokesList= new ArrayList<Product>();
	private List sodaList=new ArrayList<Product>();
	
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int i) {
		this.productQuantity = i;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}




	public List<Product> getSodaList() {
		return sodaList;
	}

	public void setSodaList(List sodaList) {
		this.sodaList = sodaList;
	}

	public List<Product> getSmokesList() {
		return smokesList;
	}

	public void setSmokesList(List smokesList) {
		this.smokesList = smokesList;
	}

	public List<Product> getGroceryList() {
		return groceryList;
	}

	public void setGroceryList(List groceryList) {
		this.groceryList = groceryList;
	}

	@Override
	public String toString() {
		return "Order [groceryList=" + groceryList + "]";
	}
	
	

}
