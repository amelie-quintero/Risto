package com.example.Risto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IngredientTests {

	private Ingredient ingredient;
	
	@BeforeEach
	void setup() {
		this.ingredient = new Ingredient();
	}
	
	@Test
	@DisplayName(value = "Test that the id field is correctly assigned at object creation")
	void testId() {
		assertNotNull(this.ingredient.getId());
	}
	
	@Test
	@DisplayName(value = "Test that the name field is correctly written and read")
	void testName() {
		this.ingredient.setName("test");
		assertEquals("test", this.ingredient.getName());
		
		this.ingredient.setName(null);
		assertNull(this.ingredient.getName());
	}
	
	@Test
	@DisplayName(value = "Test that the amount field is correctly written and read")
	void testAmount() {
		this.ingredient.setAmount(100.0);
		assertEquals(100.0, this.ingredient.getAmount());
		
		this.ingredient.setAmount(null);
		assertNull(this.ingredient.getAmount());
	}
	
	@Test
	@DisplayName(value = "Test that the lowThreshold field is correctly written and read")
	void testLowThreshold() {
		this.ingredient.setLowThreshold(50.0);
		assertEquals(50.0, this.ingredient.getLowThreshold());
		
		this.ingredient.setLowThreshold(null);
		assertNull(this.ingredient.getLowThreshold());
	}
	
//	TODO ADD VALIDATION TEST CASES
}
