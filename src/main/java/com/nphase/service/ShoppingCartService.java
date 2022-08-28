package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;

public class ShoppingCartService {

	private final BigDecimal DISCOUNT = BigDecimal.valueOf(0.1); // can be moved to config or env param

	public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
		return shoppingCart.getProducts()
				.stream()
				.map(this::calculateWithDiscountIfApplicable)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}

	private BigDecimal calculateWithDiscountIfApplicable(Product product) {
		BigDecimal amount = product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity()));
		return amount.subtract(product.getQuantity() > 3 ? amount.multiply(DISCOUNT) : BigDecimal.ZERO);
	}
}
