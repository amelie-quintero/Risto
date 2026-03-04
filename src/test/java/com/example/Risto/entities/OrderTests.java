package com.example.Risto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTests {

	private Order order;
	
	@BeforeEach
	void setup() {
		this.order = Order.builder().build();
	}
	
	@Test
	@DisplayName(value = "Test that the id field is correctly populated on object creation")
	void testId() {
		assertNotNull(this.order.getId());
	}
	
	@Test
	@DisplayName(value = "Test that the userId field is correctly written and read")
	void testUserId() {
		this.order.setUserId(1234);
		assertEquals(1234, this.order.getUserId());
		
		this.order.setUserId(0);
		assertEquals(0, this.order.getUserId());
	}
	
	@Test
	@DisplayName(value = "Test that the date field is correctly written and read")
	void testDate() {
		Date date = Date.valueOf("2000-12-31");
		this.order.setDate(date);
		assertEquals("2000-12-31", this.order.getDate().toString());
	}
	
//	TODO ADD VALIDATION TEST CASES
}
