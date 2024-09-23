package com.bnpp.katas.developmentbooks.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bnpp.katas.developmentbooks.model.BookRequest;

class CalculateBookPriceServiceTest {

	@Test
	public void calculatePriceForASingleBookPurchase_ShouldReturnPriceFifty() {

		CalculateBookPriceService calculateBookPriceService = new CalculateBookPriceService();
		BookRequest bookReq = new BookRequest(1, 1);

		double price = calculateBookPriceService.calculatePrice(bookReq);
		
		assertEquals(50.0, price);
	}

}
