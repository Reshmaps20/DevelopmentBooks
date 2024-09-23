package com.bnpp.katas.developmentbooks.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnpp.katas.developmentbooks.model.Book;
import com.bnpp.katas.developmentbooks.model.BookRequest;
import com.bnpp.katas.developmentbooks.model.BookResponse;
import com.bnpp.katas.developmentbooks.service.BookStoreService;
import com.bnpp.katas.developmentbooks.service.CalculateBookPriceService;


@RestController
@RequestMapping("/api/bookstore")
public class BookStoreController {

	private BookStoreService bookStoreService;
	private CalculateBookPriceService calculateBookPriceService;
	
	public BookStoreController(BookStoreService bookStoreService,CalculateBookPriceService calculateBookPriceService) {
		this.bookStoreService = bookStoreService;
		this.calculateBookPriceService = calculateBookPriceService;
	}
	
	@GetMapping("/fetchbooks")
	public List<Book> fetchAvailableBooks() {
		return bookStoreService.fetchBooks();
	}
	
	@PostMapping("/calculateprice")
	public BookResponse calculatePrice(@RequestBody List<BookRequest> request) {
		return calculateBookPriceService.calculatePrice(request);
	}
}
