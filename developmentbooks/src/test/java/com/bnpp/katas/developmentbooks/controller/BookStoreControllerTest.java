package com.bnpp.katas.developmentbooks.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bnpp.katas.developmentbooks.service.CalculateBookPriceService;

@SpringBootTest
@AutoConfigureMockMvc
class BookStoreControllerTest {

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
}
