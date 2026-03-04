package com.example.Risto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTests {

	private User user;
	
	@BeforeEach
	void setup() {
		this.user = new User();
	}
	
	@Test
	@DisplayName(value = "Test that the id field is correctly populated on object creation")
	void testId() {
		assertNotNull(this.user.getId());
	}
	
	@Test
	@DisplayName(value = "Test that the username field is correctly written and read")
	void testUsername() {
		this.user.setUsername("test");
		assertEquals("test", this.user.getUsername());
		
		this.user.setUsername(null);
		assertNull(this.user.getUsername());
	}
	
	@Test
	@DisplayName(value = "Test that the email field is correctly written and read")
	void testEmail() {
		this.user.setEmail("test@test.com");
		assertEquals("test@test.com", this.user.getEmail());
		
		this.user.setEmail(null);
		assertNull(this.user.getEmail());
	}
	
	@Test
	@DisplayName(value = "Test that the password field is correctly written and read")
	void testPassword() {
		this.user.setPassword("test");
		assertEquals("test", this.user.getPassword());
		
		this.user.setPassword(null);
		assertNull(this.user.getPassword());
	}
	
	@Test
	@DisplayName(value = "Test that the active field is correctly written and read")
	void testActive() {
		this.user.setActive(true);
		assertTrue(this.user.getActive());
		
		this.user.setActive(false);
		assertFalse(this.user.getActive());
		
		this.user.setActive(null);
		assertNull(this.user.getActive());
	}
	
//	TODO ADD VALIDATION TEST CASES
}
