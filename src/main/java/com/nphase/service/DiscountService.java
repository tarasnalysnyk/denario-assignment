package com.nphase.service;

import com.nphase.entity.Discount;
import com.nphase.entity.ProductCategory;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountService {
	private static final List<Discount> DISCOUNTS = List.of(
			new Discount(ProductCategory.DRINKS, BigDecimal.valueOf(0.1), 3)
	);

	public BigDecimal getDiscount(ProductCategory productCategory, int itemsCount) {
		List<Discount> discounts = DISCOUNTS.stream()
				.filter(it -> it.getProductCategory() == productCategory && it.getMinimumCount() <= itemsCount)
				.collect(Collectors.toList());
		if (discounts.size() > 1) {
			throw new IllegalStateException("Failed to determine discount: configuration issue!");
		}
		return discounts.stream().findFirst().map(Discount::getDiscount).orElse(BigDecimal.ZERO);
	}
}