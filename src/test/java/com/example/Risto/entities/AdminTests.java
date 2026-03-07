package com.example.Risto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.context.annotation.Import;

import com.example.Risto.config.TestAuditorConfig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
@Import(TestAuditorConfig.class)
public class AdminTests {
	
	@Autowired
	private TestEntityManager entityManager;
	
	private Admin admin1, admin2;
	
	@BeforeEach
	void setup() {
		this.admin1 = entityManager.persistAndFlush(Admin.builder().active(true).email("admin@example.com").username("admin").password("pw").build());
	}

	@Test
	@DisplayName(value = "Test that an Admin Id is correctly assigned when the object is created")
	void testId() {
		assertNotNull(this.admin1.getId());
	}
	
	@Test
	@DisplayName(value = "Test that the created_at and updated_at fields are set correctly by the auditor")
	void testAuditing() {
		assertNotNull(admin1.getCreatedAt());
		assertNotNull(admin1.getUpdatedAt());
		assertEquals(admin1.getCreatedAt(), admin1.getUpdatedAt());
		
		admin1.setActive(false);
		admin2 = entityManager.persistAndFlush(admin1);
		assertTrue(admin2.getUpdatedAt().isAfter(admin2.getCreatedAt()));
	}
	
	@Test
	@DisplayName(value = "Test that the Admin Username is correctly set and retrieved")
	void testUsername() {
		this.admin1.setUsername("test");
		admin2 = entityManager.persistAndFlush(admin1);
		assertEquals("test", this.admin2.getUsername());
	}
	
	@Test
	@DisplayName(value = "Test that the Admin Password is correctly set and retrieved")
	void testPassword() {
		this.admin1.setPassword("test password");
		admin2 = entityManager.persistAndFlush(admin1);
		assertEquals("test password", this.admin2.getPassword());
	}
	
	@Test
	@DisplayName(value = "Test that the Admin Email is correctly set and retrieved when valid")
	void testEmail() {
		this.admin1.setEmail("email@example.com");
		admin2 = entityManager.persistAndFlush(admin1);
		assertEquals("email@example.com", this.admin2.getEmail());
	}
	
	@Test
	@DisplayName(value = "Test that a null Admin Username will throw a ConstraintViolation Exception")
	void testInvalidUsername() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		admin1.setUsername(null);
		
		ArrayList<ConstraintViolation<Admin>> violations = new ArrayList<>(validator.validate(this.admin1));
		
		assertEquals(1, violations.size());
		assertEquals("must not be null", violations.get(0).getMessage());
	}
	
	@Test
	@DisplayName(value = "Test that a null Admin Email will throw a ConstraintViolation Exception")
	void testInvalidPassword() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		admin1.setEmail(null);
		
		ArrayList<ConstraintViolation<Admin>> violations = new ArrayList<>(validator.validate(this.admin1));
		
		assertEquals(1, violations.size());
		assertEquals("must not be null", violations.get(0).getMessage());
	}
	
	@Test
	@DisplayName(value = "Test that an invalid Admin Email will throw a ConstraintViolation Exception")
	void testInvalidEmail() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		this.admin1.setEmail("randominvalidvalue");
		
		ArrayList<ConstraintViolation<Admin>> violations = new ArrayList<>(validator.validate(this.admin1));
		
		assertEquals(1, violations.size());
		assertEquals("must be a well-formed email address", violations.get(0).getMessage());
	}
	
	@Test
	@DisplayName(value = "Test for active field")
	void testAdminfield() {
		this.admin1.setActive(false);
		admin2 = entityManager.persistAndFlush(admin1);
		assertFalse(this.admin2.getActive());
		
		this.admin1.setActive(true);
		admin2 = entityManager.persistAndFlush(admin1);
		assertTrue(this.admin2.getActive());
		
		this.admin1.setActive(null);
		admin2 = entityManager.persistAndFlush(admin1);
		assertNull(this.admin2.getActive());
	}
}
