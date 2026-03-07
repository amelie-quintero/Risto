package com.example.Risto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import jakarta.validation.ConstraintViolationException;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
@Import(TestAuditorConfig.class)
public class UserTests {
	
	@Autowired
	private TestEntityManager entityManager;

	private User user1, user2;
	
	@BeforeEach
	void setup() {
		user1 = entityManager.persistAndFlush(User.builder().active(true).email("user@example.com").password("pw").username("user").build());
	}
	
	@Test
	@DisplayName(value = "Test that the id field is correctly populated on object creation")
	void testId() {
		assertNotNull(user1.getId());
	}
	
	@Test
	@DisplayName(value = "Test that the created_at and updated_at fields are correctly set by auditor")
	void testAuditing() {
		assertNotNull(user1.getCreatedAt());
		assertNotNull(user1.getUpdatedAt());
		assertEquals(user1.getCreatedAt(), user1.getUpdatedAt());
		
		user1.setActive(false);
		user2 = entityManager.persistAndFlush(user1);
		assertTrue(user2.getUpdatedAt().isAfter(user2.getCreatedAt()));
	}
	
	@Test
	@DisplayName(value = "Test that the username field is correctly written and read")
	void testUsername() {
		user1.setUsername("test");
		user2 = entityManager.persistAndFlush(user1);
		assertEquals("test", user2.getUsername());
		
		user1.setUsername(null);
		assertThrows(ConstraintViolationException.class, () -> {entityManager.persistAndFlush(user1);});
	}
	
	@Test
	@DisplayName(value = "Test that the email field is correctly written and read")
	void testEmail() {
		user1.setEmail("test@test.com");
		user2 = entityManager.persistAndFlush(user1);
		assertEquals("test@test.com", user2.getEmail());
		
		user1.setEmail(null);
		assertNull(user1.getEmail());
		assertThrows(ConstraintViolationException.class, () -> {entityManager.persistAndFlush(user1);});
	}
	
	@Test
	@DisplayName(value = "Test that the password field is correctly written and read")
	void testPassword() {
		user1.setPassword("test");
		user2 = entityManager.persistAndFlush(user1);
		assertEquals("test", user2.getPassword());
		
		user1.setPassword(null);
		assertThrows(ConstraintViolationException.class, () -> {entityManager.persistAndFlush(user1);});
	}
	
	@Test
	@DisplayName(value = "Test that the active field is correctly written and read")
	void testActive() {
		user1.setActive(true);
		user2 = entityManager.persistAndFlush(user1);
		assertTrue(user2.getActive());
		
		user1.setActive(false);
		user2 = entityManager.persistAndFlush(user1);
		assertFalse(user2.getActive());
		
		user1.setActive(null);
		user2 = entityManager.persistAndFlush(user1);
		assertNull(user2.getActive());
	}
}
