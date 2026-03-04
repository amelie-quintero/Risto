package com.example.Risto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DishIngredientTests {

	private DishIngredient dishIngredient;
	
	@BeforeEach
	void setup() {
		this.dishIngredient = DishIngredient.builder().build();
	}
	
	@Test
	@DisplayName(value = "Test that the id field is populated correctly on object creation")
	void testId() {
		assertNotNull(this.dishIngredient.getId());
	}
	
	@Test
	@DisplayName(value = "Test that the dishId field is written and read correctly")
	void testDishId() {
		this.dishIngredient.setDishId(1234);
		assertEquals(1234, this.dishIngredient.getDishId());
		
		this.dishIngredient.setDishId(0);
		assertEquals(0, this.dishIngredient.getDishId());
	}
	
	@Test
	@DisplayName(value = "Test that the ingredientId field is written and read correctly")
	void testIngredientId() {
		this.dishIngredient.setIngredientId(1234);
		assertEquals(1234, this.dishIngredient.getIngredientId());
		
		this.dishIngredient.setIngredientId(0);
		assertEquals(0, this.dishIngredient.getIngredientId());
	}
	
//	TODO ADD VALIDATION TEST CASES
}
