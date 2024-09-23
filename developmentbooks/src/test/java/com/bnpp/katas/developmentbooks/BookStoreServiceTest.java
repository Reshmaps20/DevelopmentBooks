package com.bnpp.katas.developmentbooks;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.bnpp.katas.developmentbooks.model.Book;
import com.bnpp.katas.developmentbooks.service.BookStoreService;

class BookStoreServiceTest {

	@Test
	@DisplayName("Fetch Book should return all the available books")
	void fetchBooks_ShouldFetchAllTheAvailableBooks() {

		BookStoreService bookStoreService = new BookStoreService();
		List<Book> books = bookStoreService.fetchBooks();
		assertEquals(5, books.size());
	}

}
