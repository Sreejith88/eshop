package com.eshop.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String productName;
	private String productDesc;
	private String listPrice;
	private String sellPrice;
	private String productImagePath;
	private String createdBy;

	@CreationTimestamp
	@Column(columnDefinition = "TIMESTAMP")
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
