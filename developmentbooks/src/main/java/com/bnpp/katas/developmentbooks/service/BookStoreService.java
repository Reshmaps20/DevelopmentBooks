package com.bnpp.katas.developmentbooks.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bnpp.katas.developmentbooks.model.Book;
import com.bnpp.katas.developmentbooks.store.BooksEnum;

@Service
public class BookStoreService {

	public List<Book> fetchBooks() {
		return Arrays.stream(BooksEnum.values()).map(this::mapToBook).collect(Collectors.toList());
	}

	private Book mapToBook(BooksEnum bookEnum) {
		Book book = new Book();
		book.setId(bookEnum.getId());
		book.setTitle(bookEnum.getTitle());
		book.setAuthor(bookEnum.getAuthor());
		book.setYear(bookEnum.getYear());
		book.setPrice(bookEnum.getPrice());
		return book;
	}
}
