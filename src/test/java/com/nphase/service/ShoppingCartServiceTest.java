package com.nphase.service;


import com.nphase.entity.Product;
import com.nphase.entity.ProductCategory;
import com.nphase.entity.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class ShoppingCartServiceTest {
	private final ShoppingCartService service = new ShoppingCartService(new DiscountService());

	@Test
	public void calculatesPriceWithDiscountAndCategory() {
		ShoppingCart cart = new ShoppingCart(Arrays.asList(
				new Product("Tea", BigDecimal.valueOf(5.3), 2, ProductCategory.DRINKS),
				new Product("Coffee", BigDecimal.valueOf(3.5), 2, ProductCategory.DRINKS),
				new Product("Cheese", BigDecimal.valueOf(8), 2, ProductCategory.FOOD)
		));
		BigDecimal result = service.calculateTotalPrice(cart);
		Assertions.assertEquals(new BigDecimal("31.84"), result);
	}

}