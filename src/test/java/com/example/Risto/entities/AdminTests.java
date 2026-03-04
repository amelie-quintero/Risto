package com.example.Risto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class AdminTests {
	
	private Admin admin;
	
	@BeforeEach
	void setup() {
		this.admin = new Admin();
	}

	@Test
	@DisplayName(value = "Test that an Admin Id is correctly assigned when the object is created")
	void testId() {
		assertNotNull(this.admin.getId());
	}
	
	@Test
	@DisplayName(value = "Test that the Admin Username is correctly set and retrieved")
	void testUsername() {
		this.admin.setUsername("test");
		assertEquals("test", this.admin.getUsername());
	}
	
	@Test
	@DisplayName(value = "Test that the Admin Password is correctly set and retrieved")
	void testPassword() {
		this.admin.setPassword("test password");
		assertEquals("test password", this.admin.getPassword());
	}
	
	@Test
	@DisplayName(value = "Test that the Admin Email is correctly set and retrieved when valid")
	void testEmail() {
		this.admin.setEmail("email@example.com");
		assertEquals("email@example.com", this.admin.getEmail());
	}
	
	@Test
	@DisplayName(value = "Test that an invalid Admin Email will throw a ConstraintViolation Exception")
	void testInvalidUsername() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		this.admin.setPassword("test");
		this.admin.setEmail("email@example.com");
		
		ArrayList<ConstraintViolation<Admin>> violations = new ArrayList<>(validator.validate(this.admin));
		
		assertEquals(1, violations.size());
		assertEquals("must not be null", violations.get(0).getMessage());
	}
	
	@Test
	@DisplayName(value = "Test that an invalid Admin Email will throw a ConstraintViolation Exception")
	void testInvalidPassword() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		this.admin.setUsername("test");
		this.admin.setEmail("email@example.com");
		
		ArrayList<ConstraintViolation<Admin>> violations = new ArrayList<>(validator.validate(this.admin));
		
		assertEquals(1, violations.size());
		assertEquals("must not be null", violations.get(0).getMessage());
	}
	
	@Test
	@DisplayName(value = "Test that an invalid Admin Email will throw a ConstraintViolation Exception")
	void testInvalidEmail() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		this.admin.setPassword("test");
		this.admin.setUsername("test");
		this.admin.setEmail("randominvalidvalue");
		
		ArrayList<ConstraintViolation<Admin>> violations = new ArrayList<>(validator.validate(this.admin));
		
		assertEquals(1, violations.size());
		assertEquals("must be a well-formed email address", violations.get(0).getMessage());
	}
	
	@Test
	@DisplayName(value = "Test for active field")
	void testAdminfield() {
		this.admin.setActive(false);
		assertFalse(this.admin.getActive());
		
		this.admin.setActive(true);
		assertTrue(this.admin.getActive());
		
		this.admin.setActive(null);
		assertNull(this.admin.getActive());
	}
}
