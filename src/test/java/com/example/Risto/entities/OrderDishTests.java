package com.example.Risto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.Risto.config.TestAuditorConfig;
import com.example.Risto.constants.OrderStatus;

@DataJpaTest
@Import(TestAuditorConfig.class)
public class OrderDishTests {
	
	@Autowired
	private TestEntityManager entityManager;
	
	private User user;
	
	private Order order1;
	
	private Dish dish1;
	
	private OrderDish od1, od2;
	
	@BeforeEach
	void setup() {
		user = entityManager.persist(User.builder().active(true).email("example@example.com").username("user").password("pw").build());
		order1 = entityManager.persist(Order.builder().user(user).status(OrderStatus.PENDING).date(Date.valueOf("2000-10-10")).build());
		order1 = entityManager.persist(Order.builder().user(user).status(OrderStatus.PENDING).date(Date.valueOf("2000-12-10")).build());
		dish1 = entityManager.persist(Dish.builder().name("dish1").price(12.0).build());
		dish1 = entityManager.persist(Dish.builder().name("dish2").price(16.0).build());
		od1 = entityManager.persistAndFlush(OrderDish.builder().order(order1).dish(dish1).amount(2).build());
	}
	
	@Test
	@DisplayName(value = "Test that the id field is correctly populated on object creation")
	void testId() {
		assertNotEquals(0, od1.getId());
	}
	
	@Test
	@DisplayName(value = "Test that the created_at and updated_at value are correctly set by the auditor")
	void testAuditing() {
		assertNotNull(od1.getCreatedAt());
		assertNotNull(od1.getUpdatedAt());
		assertEquals(od1.getCreatedAt(), od1.getUpdatedAt());
		
		od1.setAmount(4);
		OrderDish od2 = entityManager.persistAndFlush(od1);
		assertTrue(od2.getUpdatedAt().isAfter(od2.getCreatedAt()));
	}
	
	@Test
	@DisplayName(value = "Test that the amount field is correctly written and read")
	void testAmount() {
		od1.setAmount(3);
		od2 = entityManager.persistAndFlush(od1);
		assertEquals(3, od2.getAmount());
	}
}
