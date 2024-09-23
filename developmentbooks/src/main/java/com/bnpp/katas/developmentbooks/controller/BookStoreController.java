package com.bnpp.katas.developmentbooks.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnpp.katas.developmentbooks.model.Book;
import com.bnpp.katas.developmentbooks.service.BookStoreService;

@RestController
@RequestMapping("/api/bookstore")
public class BookStoreController {

	private BookStoreService bookStoreService;
	
	public BookStoreController(BookStoreService bookStoreService) {
		this.bookStoreService = bookStoreService;
	}
	
	@GetMapping("/fetchbooks")
	public List<Book> fetchAvailableBooks() {
		return bookStoreService.fetchBooks();
	}
}
