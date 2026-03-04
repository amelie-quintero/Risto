package com.example.Risto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DishTests {

	private Dish dish;
	
	@BeforeEach
	void setup() {
		this.dish = Dish.builder().build();
	}
	
	@Test
	@DisplayName(value = "Test that the dish id field is correctly assigned when the object is created")
	void testId() {
		assertNotNull(this.dish.getId());
	}
	
	@Test
	@DisplayName(value = "Test that the name field can be written and read correctly")
	void testName() {
		this.dish.setName("test");
		assertEquals("test", this.dish.getName());
		
		this.dish.setName(null);
		assertNull(this.dish.getName());
	}
	
	@Test
	@DisplayName(value = "Test that the price field can be written and read correctly")
	void testPrice() {
		this.dish.setPrice(Double.valueOf(10.0));
		assertEquals(Double.valueOf(10.00), this.dish.getPrice());
		
		this.dish.setPrice(null);
		assertNull(this.dish.getPrice());
	}
	
	//TODO ADD VALIDATION TESTS
}
