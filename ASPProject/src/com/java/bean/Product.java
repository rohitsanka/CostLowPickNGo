package com.java.bean;


public class Product{
	
	private String productID;
	private String productName;
	private Integer productQuantity;
	
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName="
				+ productName + ", productQuantity=" + productQuantity + "]";
	}
	

}
