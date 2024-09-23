package com.bnpp.katas.developmentbooks.service;

import java.util.Arrays;
import org.springframework.stereotype.Service;
import com.bnpp.katas.developmentbooks.store.BooksEnum;

@Service
public class CalculateBookPriceService {

	public double calculatePrice(int bookId, int quantity) {
	
		BooksEnum book = Arrays.stream(BooksEnum.values())
		            .filter(b -> b.getId() == bookId)
		            .findFirst()
		            .orElse(null);
		return book.getPrice() * quantity;
	}

}
