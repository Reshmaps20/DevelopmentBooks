package com.bnpp.katas.developmentbooks.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bnpp.katas.developmentbooks.model.BookRequest;
import com.bnpp.katas.developmentbooks.service.CalculateBookPriceService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class BookStoreControllerTest {

	private static final int ONE = 1;
	private static final int TWO = 2;

	@Mock
	private CalculateBookPriceService calculateBookPriceService;

	@Autowired
	MockMvc mockMvc;

	@Test
	@DisplayName("Fetch the available Books")
	void fetchAvailableBooks_ShouldRetreiveTheBooks() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/bookstore/fetchbooks").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("API to calculate the price of book")
	void fetchPriceSummaary_Api_shouldReturn_StatusOK() throws Exception {
		List<BookRequest> listOfBooks = new ArrayList<BookRequest>();
		BookRequest firstBook = new BookRequest(ONE, ONE);
		BookRequest secondBook = new BookRequest(TWO, TWO);
		listOfBooks.add(firstBook);
		listOfBooks.add(secondBook);

		mockMvc.perform(post("/api/bookstore/calculateprice").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(listOfBooks))).andExpect(status().isOk());
	}
}
