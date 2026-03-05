package com.example.Risto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.sql.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.AutoConfigureTestEntityManager;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import com.example.Risto.constants.OrderStatus;

@DataJpaTest
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
	@DisplayName(value = "Test that the amount field is correctly written and read")
	void testAmount() {
		od1.setAmount(3);
		od2 = entityManager.persistAndFlush(od1);
		assertEquals(3, od2.getAmount());
	}
}
