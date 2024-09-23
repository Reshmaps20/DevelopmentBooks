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
import com.bnpp.katas.developmentbooks.store.DiscountEnum;

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
		while (hasBooksLeft(bookCountsCopy)) {

			List<BooksEnum> selectedBooks = selectBooks(bookCountsCopy);

			if (!selectedBooks.isEmpty()) {
				double discount = getDiscount(selectedBooks.size());
				double actualPrice = selectedBooks.size() * 50;
				totalPrice += actualPrice * (1 - discount);
			}
		}
		return totalPrice;
	}

	private List<BooksEnum> selectBooks(Map<BooksEnum, Integer> bookCountsCopy) {
		List<BooksEnum> selectedBooks = new ArrayList<>();
		for (BooksEnum bookEnum : BooksEnum.values()) {
			if (bookCountsCopy.getOrDefault(bookEnum, 0) > 0) {
				selectedBooks.add(bookEnum);
				bookCountsCopy.put(bookEnum, bookCountsCopy.get(bookEnum) - 1);
			}
		}
		return selectedBooks;
	}

	private boolean hasBooksLeft(Map<BooksEnum, Integer> bookCountsCopy) {
		return bookCountsCopy.values().stream().anyMatch(count -> count > 0);
	}

	private void addBook(List<BookRequest> bookRequest) {
		for (BookRequest request : bookRequest) {
			BooksEnum book = BooksEnum.values()[request.getId() - 1];
			bookCounts.put(book, request.getQuantity());
		}
	}

	public static double getDiscount(int uniqueBookCount) {
		return Arrays.stream(DiscountEnum.values())
				.filter(discount -> discount.getNumberOfDistinctItems() == uniqueBookCount)
				.map(DiscountEnum::getDiscountPercentage).findFirst().orElse(0.0);
	}
}
