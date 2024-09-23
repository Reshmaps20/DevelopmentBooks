package com.bnpp.katas.developmentbooks.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bnpp.katas.developmentbooks.model.BookRequest;
import com.bnpp.katas.developmentbooks.store.BooksEnum;

@Service
public class CalculateBookPriceService {

	private Map<BooksEnum, Integer> bookCounts;

	public CalculateBookPriceService() {
		this.bookCounts = new LinkedHashMap<>();
	}

	public double calculatePrice(List<BookRequest> bookRequest) {

		double totalPrice = 0.0;
		addBook(bookRequest);

		Map<BooksEnum, Integer> bookCountsCopy = new HashMap<>(bookCounts);
		while ((bookCountsCopy.values().stream().anyMatch(count -> count > 0))) {

			List<BooksEnum> selectedBooks = new ArrayList<>();
			for (BooksEnum bookEnum : BooksEnum.values()) {
				if (bookCountsCopy.getOrDefault(bookEnum, 0) > 0) {
					selectedBooks.add(bookEnum);
					bookCountsCopy.put(bookEnum, bookCountsCopy.get(bookEnum) - 1);
				}
			}
			if (!selectedBooks.isEmpty()) {
				double discount = getDiscount(selectedBooks.size());
				double actualPrice = selectedBooks.size() * 50;
				totalPrice += actualPrice * (1 - discount);
			}
		}
		return totalPrice;
	}

	private void addBook(List<BookRequest> bookRequest) {
		for (BookRequest request : bookRequest) {
			BooksEnum book = BooksEnum.values()[request.getId() - 1];
			bookCounts.put(book, request.getQuantity());
		}
	}

	private double getDiscount(int bookCount) {
		if (bookCount == 3) {
			return 0.10;
		} else if (bookCount == 2) {
			return 0.05;
		} else {
			return 0.0;
		}
	}

}
