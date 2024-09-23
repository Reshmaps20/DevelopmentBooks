package com.bnpp.katas.developmentbooks.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculateBookPriceServiceTest {

	@Test
	public void calculatePriceForASingleBookPurchase_ShouldReturnPriceFifty() {
		
		CalculateBookPriceService calculateBookPriceService = new CalculateBookPriceService();
		
		double price = calculateBookPriceService.calculatePrice(1,1);
		assertEquals(50.0, price);
	}

}
