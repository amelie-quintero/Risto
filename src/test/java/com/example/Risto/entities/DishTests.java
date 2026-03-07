package com.example.Risto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.context.annotation.Import;

import com.example.Risto.config.TestAuditorConfig;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
@Import(TestAuditorConfig.class)
public class DishTests {
	
	@Autowired
	private TestEntityManager entityManager;

	private Dish dish, dish2;
	
	@BeforeEach
	void setup() {
		dish = entityManager.persistAndFlush(Dish.builder().name("test").price(10.0).build());
	}
	
	@Test
	@DisplayName(value = "Test that the dish id field is correctly assigned when the object is created")
	void testId() {
		assertNotNull(dish.getId());
	}
	
	@Test
	@DisplayName(value = "Test that the created_at and updated_at fields are correctly set by the auditor")
	void testAuditing() {
		assertNotNull(dish.getCreatedAt());
		assertNotNull(dish.getUpdatedAt());
		assertEquals(dish.getCreatedAt(), dish.getUpdatedAt());
		
		dish.setPrice(1.0);
		dish2 = entityManager.persistAndFlush(dish);
		assertTrue(dish2.getUpdatedAt().isAfter(dish2.getCreatedAt()));
	}
	
	@Test
	@DisplayName(value = "Test that the name field can be written and read correctly")
	void testName() {
		dish.setName("test");
		dish2 = entityManager.persistAndFlush(dish);
		assertEquals("test", dish2.getName());
	}
	
	@Test
	@DisplayName(value = "Test that the price field can be written and read correctly")
	void testPrice() {
		dish.setPrice(Double.valueOf(10.0));
		dish2 = entityManager.persistAndFlush(dish);
		assertEquals(Double.valueOf(10.00), dish2.getPrice());
	}
}
