package com.example.Risto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderDishTests {

	private OrderDish orderDish;
	
	@BeforeEach
	void setup() {
		this.orderDish = new OrderDish();
	}
	
	@Test
	@DisplayName(value = "Test that the id field is correctly populated on object creation")
	void testId() {
		assertNotNull(this.orderDish.getId());
	}
	
	@Test
	@DisplayName(value = "Test that the orderId field is correctly written and read")
	void testOrderId() {
		this.orderDish.setOrderId(1234);
		assertEquals(1234, this.orderDish.getOrderId());
		
		this.orderDish.setOrderId(0);
		assertEquals(0, this.orderDish.getOrderId());
	}
	
	@Test
	@DisplayName(value = "Test that the dishId field is correctly written and read")
	void testDishId() {
		this.orderDish.setDishId(1234);
		assertEquals(1234, this.orderDish.getDishId());
		
		this.orderDish.setDishId(0);
		assertEquals(0, this.orderDish.getDishId());
	}
	
//	TODO ADD VALIDATION TEST CASES
}
