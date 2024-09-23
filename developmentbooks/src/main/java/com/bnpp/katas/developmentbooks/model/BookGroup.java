package com.bnpp.katas.developmentbooks.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookGroup {

	private List<Integer> listOfBooks;
	private double discountPercentage;
	private double actualPrice;
	private double discount;
	private int numberOfBooks;
}
