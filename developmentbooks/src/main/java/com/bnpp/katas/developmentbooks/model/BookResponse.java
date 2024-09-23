package com.bnpp.katas.developmentbooks.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

	private List<BookGroup> listOfBookGroups;
	private double actualPrice;
	private double totalDiscount;
	private double finalPrice;
}
