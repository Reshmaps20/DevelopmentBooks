package com.bnpp.katas.developmentbooks.store;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BooksEnumTest {

	private static final int NUMBER_OF_DEVELOPMENT_BOOKS = 5;

	@Test
	@DisplayName("BooksEnum should contain five books")
	void booksEnum_shouldContainFiveBooks() {

		assertEquals(NUMBER_OF_DEVELOPMENT_BOOKS, BooksEnum.values().length);
	}

}
