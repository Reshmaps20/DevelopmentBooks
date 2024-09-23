package com.bnpp.katas.developmentbooks.store;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountEnum {

	TWO_BOOKS(2, 0.05), 
	THREE_BOOKS(3, 0.10), 
	FOUR_BOOKS(4, 0.20), 
	FIVE_BOOKS(5, 0.25);
	
	private final int numberOfDistinctItems;
    private final double discountPercentage;
    
	public static final double PRICE = 50.0;
}
