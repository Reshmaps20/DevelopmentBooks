package com.bnpp.katas.developmentbooks.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class BookStoreControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	@DisplayName("Fetch the available Books")
	void fetchAvailableBooks_ShouldRetreiveTheBooks() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/bookstore/fetchbooks")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
