package com.nphase.entity;

import java.math.BigDecimal;

public class Discount {
	private final ProductCategory productCategory;
	private final BigDecimal discount;
	private final int minimumCount;

	public Discount(ProductCategory productCategory, BigDecimal discount, int minimumCount) {
		this.productCategory = productCategory;
		this.discount = discount;
		this.minimumCount = minimumCount;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public int getMinimumCount() {
		return minimumCount;
	}
}