package com.bnpp.katas.developmentbooks.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import com.bnpp.katas.developmentbooks.store.BooksEnum;

@Service
public class BookStoreService {

	public List<BooksEnum> fetchBooks() {
		return Arrays.asList(BooksEnum.values());
	}

}
