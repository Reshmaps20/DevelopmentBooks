package com.bnpp.katas.developmentbooks.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.bnpp.katas.developmentbooks.model.BookRequest;

class CalculateBookPriceServiceTest {

	private CalculateBookPriceService calculateBookPriceService;
	List<BookRequest> listOfBooks;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIFTH = 5;
	private static final double BOOK_PRICE = 50.00;
	private static final double PRICE_OF_TWO_DISTINCT_BOOKS = 95.00;

	@BeforeEach
	public void setup() {
		calculateBookPriceService = new CalculateBookPriceService();
		listOfBooks = new ArrayList<BookRequest>();
	}

	@Test
	@DisplayName("Calculate price of a single book")
	public void calculatePriceForASingleBookPurchase_ShouldReturnPriceFifty() {
		BookRequest bookReq = new BookRequest(ONE, ONE);
		listOfBooks.add(bookReq);

		double price = calculateBookPriceService.calculatePrice(listOfBooks);

		assertEquals(BOOK_PRICE, price);
	}

	@Test
	@DisplayName("Price of two different book should give 5% discount")
	public void calculatePriceForTwoDifferentBookPurchase_ShouldApplyFivePercentDiscount() {
		BookRequest firstBook = new BookRequest(ONE, ONE);
		BookRequest secondBook = new BookRequest(TWO, ONE);
		listOfBooks.add(firstBook);
		listOfBooks.add(secondBook);

		double price = calculateBookPriceService.calculatePrice(listOfBooks);

		assertEquals(PRICE_OF_TWO_DISTINCT_BOOKS, price);
	}

	@Test
	@DisplayName("Price of three different book should give 10% discount")
	public void calculatePriceForThreeDifferentBookPurchase_ShouldGive10PercentDiscount() {
		BookRequest firstBook = new BookRequest(ONE, ONE);
		BookRequest secondBook = new BookRequest(TWO, ONE);
		BookRequest thirdBook = new BookRequest(THREE, ONE);
		listOfBooks.add(firstBook);
		listOfBooks.add(secondBook);
		listOfBooks.add(thirdBook);

		double price = calculateBookPriceService.calculatePrice(listOfBooks);

		assertEquals(135.0, price);
	}

	@Test
	@DisplayName("Price of three different book with multiple copies should give best minimum price")
	public void CalculatePricForThreeDifferentBookWithMutlipleCopies_RetrunsMinimumTotalPrice() {
		BookRequest firstBook = new BookRequest(ONE, TWO);
		BookRequest secondBook = new BookRequest(TWO, TWO);
		BookRequest thirdBook = new BookRequest(THREE, ONE);
		listOfBooks.add(firstBook);
		listOfBooks.add(secondBook);
		listOfBooks.add(thirdBook);

		double price = calculateBookPriceService.calculatePrice(listOfBooks);

		assertEquals(230.0, price);
	}

	@Test
	@DisplayName("Price of four different book should give 20% discount")
	public void calculatePriceForFourDifferentBookPurchase_ShouldGive20PercentDiscount() {
		BookRequest firstBook = new BookRequest(ONE, ONE);
		BookRequest secondBook = new BookRequest(TWO, ONE);
		BookRequest thirdBook = new BookRequest(THREE, ONE);
		BookRequest forthBook = new BookRequest(FOUR, ONE);
		listOfBooks.add(firstBook);
		listOfBooks.add(secondBook);
		listOfBooks.add(thirdBook);
		listOfBooks.add(forthBook);

		double price = calculateBookPriceService.calculatePrice(listOfBooks);

		assertEquals(160.0, price);
	}

	@Test
	@DisplayName("Price of five different book should give 25% discount")
	public void calculatePriceForFiveDifferentBookPurchase_ShouldGive25PercentDiscount() {
		BookRequest firstBook = new BookRequest(ONE, ONE);
		BookRequest secondBook = new BookRequest(TWO, ONE);
		BookRequest thirdBook = new BookRequest(THREE, ONE);
		BookRequest forthBook = new BookRequest(FOUR, ONE);
		BookRequest fifthBook = new BookRequest(FIFTH, ONE);
		listOfBooks.add(firstBook);
		listOfBooks.add(secondBook);
		listOfBooks.add(thirdBook);
		listOfBooks.add(forthBook);
		listOfBooks.add(fifthBook);

		double price = calculateBookPriceService.calculatePrice(listOfBooks);

		assertEquals(187.5, price);
	}
}
