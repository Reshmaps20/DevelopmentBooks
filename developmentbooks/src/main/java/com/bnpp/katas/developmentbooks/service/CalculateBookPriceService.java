package com.bnpp.katas.developmentbooks.service;

import java.util.Arrays;
import org.springframework.stereotype.Service;

import com.bnpp.katas.developmentbooks.model.BookRequest;
import com.bnpp.katas.developmentbooks.store.BooksEnum;

@Service
public class CalculateBookPriceService {

	public double calculatePrice(BookRequest bookReq) {
	
		BooksEnum book = Arrays.stream(BooksEnum.values())
		            .filter(b -> b.getId() == bookReq.getId())
		            .findFirst()
		            .orElse(null);
		return book.getPrice() * bookReq.getQuantity();
	}

}
