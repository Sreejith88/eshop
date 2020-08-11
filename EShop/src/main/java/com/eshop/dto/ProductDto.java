package com.eshop.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

public class ProductDto {

	private Long id;
	
	@NotEmpty(message = "Product Name is Required !!")
	private String productName;
	
	@NotEmpty(message = "Product Description is Required !!")
	private String productDesc;
	
	@NotEmpty(message = "Product Listprice is Required !!")
	private String listPrice;
	
	@NotEmpty(message = "Product Sellprice is Required !!")
	private String sellPrice;
	
	private String productImagePath;
	
	private String createdBy;
	
	private LocalDateTime createdAt;
	
	public Long getId() {
		return id;
	}
	public void setId(final Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(final String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(final String productDesc) {
		this.productDesc = productDesc;
	}
	public String getListPrice() {
		return listPrice;
	}
	public void setListPrice(final String listPrice) {
		this.listPrice = listPrice;
	}
	public String getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(final String sellPrice) {
		this.sellPrice = sellPrice;
	}
	public String getProductImagePath() {
		return productImagePath;
	}
	public void setProductImagePath(final String productImagePath) {
		this.productImagePath = productImagePath;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(final LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
