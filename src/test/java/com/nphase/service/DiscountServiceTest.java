package com.nphase.service;

import com.nphase.entity.ProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class DiscountServiceTest {
	private final DiscountService service = new DiscountService();

	@Test
	public void getByCategoryAndItemsCountMatching() {
		Assertions.assertEquals(
				new BigDecimal("0.1"),
				service.getDiscount(ProductCategory.DRINKS, 3)
		);
	}

	@Test
	public void getByCategoryAndItemsCountNotMatching() {
		Assertions.assertEquals(
				BigDecimal.ZERO,
				service.getDiscount(ProductCategory.DRINKS, 2)
		);
	}
}
