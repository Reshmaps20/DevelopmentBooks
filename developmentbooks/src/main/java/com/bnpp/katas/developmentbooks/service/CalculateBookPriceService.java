package com.bnpp.katas.developmentbooks.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bnpp.katas.developmentbooks.model.BookGroup;
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

		addBook(bookRequest);
		List<Double> possiblePrices = new ArrayList<>();

		List<Integer> applicableDiscounts = getApplicableDiscounts(bookCounts.size());

		applicableDiscounts.forEach(numberOfSet -> {
			double totalPriceForBooks = calculateCombinationPrice(numberOfSet);
			possiblePrices.add(totalPriceForBooks);
		});

		bookCounts.clear();
		return possiblePrices.stream().min(Double::compare).orElse(0.0);
	}

	private List<Integer> getApplicableDiscounts(int numberOfBooks) {

		List<Integer> applicableDiscounts = Arrays.stream(DiscountEnum.values())
				.filter(level -> level.getNumberOfDistinctItems() <= numberOfBooks)
				.map(DiscountEnum::getNumberOfDistinctItems).sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());

		return applicableDiscounts.isEmpty() ? Collections.singletonList(1) : applicableDiscounts;
	}

	private double calculateCombinationPrice(int numberOfBooks) {

		Map<BooksEnum, Integer> bookCountsCopy = new HashMap<>(bookCounts);
		Map<Double, List<BookGroup>> priceToGroupMap = new HashMap<>();
		List<BookGroup> bookGroups = new ArrayList<>();
		double totalPrice = 0.0;

		while (hasBooksLeft(bookCountsCopy)) {

			List<BooksEnum> selectedBooks = selectBooks(bookCountsCopy, numberOfBooks);
			if (!selectedBooks.isEmpty()) {
				double discount = getDiscount(selectedBooks.size());
				double actualPrice = selectedBooks.size() * 50;
				totalPrice += actualPrice * (1 - discount);
				
				BookGroup group = createBookGroup(selectedBooks, discount, actualPrice);
				bookGroups.add(group);
			}
		}
		return totalPrice;
	}

	private BookGroup createBookGroup(List<BooksEnum> selectedBooks, double discount, double actualPrice) {
		return new BookGroup(selectedBooks.stream().map(BooksEnum::getId).collect(Collectors.toList()),
				discount * 100, actualPrice, actualPrice * discount, selectedBooks.size());
	}
	
	private List<BooksEnum> selectBooks(Map<BooksEnum, Integer> bookCountsCopy, int numberOfBooks) {

		List<BooksEnum> selectedBooks = new ArrayList<>();

		Arrays.stream(BooksEnum.values()).filter(bookEnum -> bookCountsCopy.getOrDefault(bookEnum, 0) > 0)
				.forEach(bookEnum -> {
					if (selectedBooks.size() < numberOfBooks) {
						selectedBooks.add(bookEnum);
						bookCountsCopy.put(bookEnum, bookCountsCopy.get(bookEnum) - 1);
					}
				});

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
