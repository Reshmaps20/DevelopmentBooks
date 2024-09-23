package com.bnpp.katas.developmentbooks.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookResponse {

	private List<BookGroup> listOfBookGroups;
	private double actualPrice;
	private double totalDiscount;
	private double finalPrice;
}
