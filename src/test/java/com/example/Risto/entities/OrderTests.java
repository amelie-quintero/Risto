package com.example.Risto.entities;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

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
import com.example.Risto.constants.OrderStatus;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
@Import(TestAuditorConfig.class)
public class OrderTests {

	@Autowired
	private TestEntityManager entityManager;
	
	private User user1, user2;
	
	private Order o1, o2;
	
	@BeforeEach
	void setup() {
		user1 = entityManager.persist(User.builder().active(true).email("user1@example.com").username("user1").password("pw1").build());
		user2 = entityManager.persist(User.builder().active(true).email("user2@example.com").username("user2").password("pw2").build());
		o1 = entityManager.persistAndFlush(Order.builder().date(LocalDateTime.of(2000,12,12, 0, 0)).status(OrderStatus.PENDING).user(user1).build());
	}
	
	@Test
	@DisplayName(value = "Test that the id field is correctly populated on object creation")
	void testId() {
		assertNotEquals(0, o1.getId());
	}
	
	@Test
	@DisplayName(value = "Test that the created_at and updated_at fields are being set properly by auditor")
	void testAuditing() {
		assertNotNull(o1.getCreatedAt());
		assertNotNull(o1.getUpdatedAt());
		assertEquals(o1.getCreatedAt(), o1.getUpdatedAt());
		
		o1.setStatus(OrderStatus.INPROGRESS);
		Order o2 = entityManager.persistAndFlush(o1);
		assertTrue(o2.getUpdatedAt().isAfter(o2.getCreatedAt()));
	}
	
	@Test
	@DisplayName(value = "Test that the date field is correctly written and read")
	void testDate() {
		LocalDateTime date = LocalDateTime.of(2000,12,31, 0, 0);
		o1.setDate(date);
		o2 = entityManager.persistAndFlush(o1);
		assertEquals(date, o2.getDate());
	}
	
	@Test
	@DisplayName(value = "Test that the status field is correctly written and read")
	void testStatus() {
		o1.setStatus(OrderStatus.INPROGRESS);
		o2 = entityManager.persistAndFlush(o1);
		assertEquals(OrderStatus.INPROGRESS, o2.getStatus());
	}
	
	@Test
	@DisplayName(value = "Test that the User field can be correctly written and set")
	void testUser() {
		o1.setUser(user2);
		o2 = entityManager.persistAndFlush(o1);
		assertEquals(user2, o2.getUser());
	}
}
