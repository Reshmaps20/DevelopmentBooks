package com.bnpp.katas.developmentbooks.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.bnpp.katas.developmentbooks.model.BookRequest;

class CalculateBookPriceServiceTest {

	@Test
	public void calculatePriceForASingleBookPurchase_ShouldReturnPriceFifty() {

		CalculateBookPriceService calculateBookPriceService = new CalculateBookPriceService();
		List<BookRequest> listOfBooks = new ArrayList<BookRequest>();
		BookRequest bookReq = new BookRequest(1, 1);
		listOfBooks.add(bookReq);

		double price = calculateBookPriceService.calculatePrice(listOfBooks);

		assertEquals(50.0, price);
	}

	@Test
	public void calculatePriceForTwoDifferentBookPurchase_ShouldApplyFivePercentDiscount() {
		
		CalculateBookPriceService calculateBookPriceService = new CalculateBookPriceService();
		List<BookRequest> listOfBooks = new ArrayList<BookRequest>();
		BookRequest firstBook = new BookRequest(1, 1);
		BookRequest secondBook = new BookRequest(2, 1);
		listOfBooks.add(firstBook);
		listOfBooks.add(secondBook);
		
		double price = calculateBookPriceService.calculatePrice(listOfBooks);
		
		assertEquals(95.0, price);
	}

}
