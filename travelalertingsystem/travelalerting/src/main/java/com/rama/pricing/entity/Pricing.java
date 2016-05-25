package com.rama.pricing.entity;


public class Pricing {

	private Integer pricingId;
	private Double price;
	
	private Integer productId;
	private String productName;
	private String productType;
	public Integer getPricingId() {
		return pricingId;
	}
	public void setPricingId(Integer pricingId) {
		this.pricingId = pricingId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
	
}
