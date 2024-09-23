package com.bnpp.katas.developmentbooks.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookRequest {

	private int id;
    private int quantity;
}
