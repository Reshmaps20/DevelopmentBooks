package com.bnpp.katas.developmentbooks.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bnpp.katas.developmentbooks.model.BookRequest;
import com.bnpp.katas.developmentbooks.store.BooksEnum;

@Service
public class CalculateBookPriceService {

	public double calculatePrice(List<BookRequest> bookRequest) {

		return bookRequest.stream().mapToDouble(bookReq -> {
			BooksEnum book = Arrays.stream(BooksEnum.values())
					.filter(b -> b.getId() == bookReq.getId()).findFirst()
					.orElse(null);
			return (book != null) ? book.getPrice() * bookReq.getQuantity() : 0.0;
		}).sum();

	}

}
