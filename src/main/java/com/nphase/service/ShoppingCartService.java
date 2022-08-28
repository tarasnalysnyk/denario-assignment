package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ProductCategory;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartService {

	private final DiscountService discountService;

	public ShoppingCartService(DiscountService discountService) {
		this.discountService = discountService;
	}

	public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
		return shoppingCart.getProducts()
				.stream()
				.collect(Collectors.groupingBy(Product::getProductCategory)).entrySet().stream()
				.map(productsCategoryEntry -> calculateGroupTotalPrize(productsCategoryEntry.getKey(), productsCategoryEntry.getValue()))
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}

	private BigDecimal calculateGroupTotalPrize(ProductCategory productCategory, List<Product> products) {
		BigDecimal discount = discountService.getDiscount(
				productCategory,
				products.stream().mapToInt(Product::getQuantity).sum()
		);
		return products.stream()
				.map(product -> calculateWithDiscount(product, discount))
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}

	private BigDecimal calculateWithDiscount(Product product, BigDecimal discount) {
		BigDecimal amount = product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity()));
		return amount.subtract(discount.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : amount.multiply(discount));
	}
}
