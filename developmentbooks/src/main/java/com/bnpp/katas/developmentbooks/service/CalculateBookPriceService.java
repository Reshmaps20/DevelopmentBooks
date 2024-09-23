package com.bnpp.katas.developmentbooks.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bnpp.katas.developmentbooks.model.BookGroup;
import com.bnpp.katas.developmentbooks.model.BookRequest;
import com.bnpp.katas.developmentbooks.model.BookResponse;
import com.bnpp.katas.developmentbooks.store.BooksEnum;
import com.bnpp.katas.developmentbooks.store.DiscountEnum;

@Service
public class CalculateBookPriceService {

	private Map<BooksEnum, Integer> bookCounts;

	public CalculateBookPriceService() {
		this.bookCounts = new LinkedHashMap<>();
	}

	public BookResponse calculatePrice(List<BookRequest> bookRequest) {

		addBook(bookRequest);

		List<Integer> applicableDiscounts = getApplicableDiscounts(bookCounts.size());

		Map<Double, List<BookGroup>> totalPrices = applicableDiscounts.stream().flatMap(numberOfBooks -> {
			Map<Double, List<BookGroup>> priceGroupMap = calculateCombinationPrice(numberOfBooks);
			return priceGroupMap.entrySet().stream();
		}).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		return findMinPriceResponse(totalPrices);
	}

	private BookResponse findMinPriceResponse(Map<Double, List<BookGroup>> totalPrices) {

		Optional<Double> minPrice = totalPrices.keySet().stream().reduce(Double::min);
		List<BookGroup> associatedBookGroups = totalPrices.get(minPrice.get());

		return createBookResponse(associatedBookGroups, minPrice.get());
	}

	private BookResponse createBookResponse(List<BookGroup> bookGroups, double finalPrice) {

		BookResponse response = new BookResponse();

		double actualPrice = calculateActualPrice(bookGroups);
		double totalDiscount = calculateTotalDiscount(bookGroups);

		response.setListOfBookGroups(bookGroups);
		response.setActualPrice(actualPrice);
		response.setTotalDiscount(totalDiscount);
		response.setFinalPrice(finalPrice);
		bookCounts.clear();
		return response;
	}

	private double calculateTotalDiscount(List<BookGroup> bookGroups) {
		return bookGroups.stream().mapToDouble(BookGroup::getDiscount).sum();
	}

	private double calculateActualPrice(List<BookGroup> bookGroups) {
		return bookGroups.stream().mapToDouble(BookGroup::getActualPrice).sum();
	}

	private List<Integer> getApplicableDiscounts(int numberOfBooks) {

		List<Integer> applicableDiscounts = Arrays.stream(DiscountEnum.values())
				.filter(level -> level.getNumberOfDistinctItems() <= numberOfBooks)
				.map(DiscountEnum::getNumberOfDistinctItems).sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());

		return applicableDiscounts.isEmpty() ? Collections.singletonList(1) : applicableDiscounts;
	}

	private Map<Double, List<BookGroup>> calculateCombinationPrice(int numberOfBooks) {

		Map<BooksEnum, Integer> bookCountsCopy = new HashMap<>(bookCounts);
		Map<Double, List<BookGroup>> priceToGroupMap = new HashMap<>();
		List<BookGroup> bookGroups = new ArrayList<>();
		double[] totalPrice = { 0.0 };

		processBooks(bookCountsCopy, numberOfBooks, bookGroups, totalPrice);

		priceToGroupMap.put(totalPrice[0], bookGroups);

		return priceToGroupMap;
	}

	private void processBooks(Map<BooksEnum, Integer> bookCountsCopy, int numberOfBooks, List<BookGroup> bookGroups,
			double[] totalPrice) {

		if (!hasBooksLeft(bookCountsCopy)) {
			return;
		}

		List<BooksEnum> selectedBooks = selectBooks(bookCountsCopy, numberOfBooks);

		if (!selectedBooks.isEmpty()) {
			double discount = getDiscount(selectedBooks.size());

			double actualPrice = selectedBooks.size() * DiscountEnum.PRICE;
			totalPrice[0] += actualPrice * (1 - discount);

			BookGroup group = createBookGroup(selectedBooks, discount, actualPrice);
			bookGroups.add(group);

			processBooks(bookCountsCopy, numberOfBooks, bookGroups, totalPrice);
		}
	}

	private BookGroup createBookGroup(List<BooksEnum> selectedBooks, double discount, double actualPrice) {
		return new BookGroup(selectedBooks.stream().map(BooksEnum::getId).collect(Collectors.toList()), discount * 100,
				actualPrice, actualPrice * discount, selectedBooks.size());
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
