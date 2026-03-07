package com.example.Risto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class IngredientTests {
	
	@Autowired
	private TestEntityManager entityManager;

	private Ingredient ingredient1, ingredient2;
	
	@BeforeEach
	void setup() {
		ingredient1 = entityManager.persistAndFlush(Ingredient.builder().amount(30.0).lowThreshold(10.0).name("test").build());
	}
	
	@Test
	@DisplayName(value = "Test that the id field is correctly assigned at object creation")
	void testId() {
		assertNotNull(ingredient1.getId());
	}
	
	@Test
	@DisplayName(value = "Test that the created_at and updated_at fields are set correctly by the auditor")
	void testAuditing() {
		assertNotNull(ingredient1.getCreatedAt());
		assertNotNull(ingredient1.getUpdatedAt());
		assertEquals(ingredient1.getCreatedAt(), ingredient1.getUpdatedAt());
		
		ingredient1.setAmount(20.0);
		ingredient2 = entityManager.persistAndFlush(ingredient1);
		assertTrue(ingredient2.getUpdatedAt().isAfter(ingredient2.getCreatedAt()));
	}
	
	@Test
	@DisplayName(value = "Test that the name field is correctly written and read")
	void testName() {
		ingredient1.setName("test");
		ingredient2 = entityManager.persistAndFlush(ingredient1);
		assertEquals("test", ingredient2.getName());
		
		ingredient1.setName(null);
		assertThrows(ConstraintViolationException.class, () -> {entityManager.persistAndFlush(ingredient1);});
	}
	
	@Test
	@DisplayName(value = "Test that the amount field is correctly written and read")
	void testAmount() {
		ingredient1.setAmount(100.0);
		ingredient2 = entityManager.persistAndFlush(ingredient1);
		assertEquals(100.0, ingredient2.getAmount());
		
		ingredient1.setAmount(null);
		assertThrows(ConstraintViolationException.class, () -> {entityManager.persistAndFlush(ingredient1);});
	}
	
	@Test
	@DisplayName(value = "Test that the lowThreshold field is correctly written and read")
	void testLowThreshold() {
		ingredient1.setLowThreshold(50.0);
		ingredient2 = entityManager.persistAndFlush(ingredient1);
		assertEquals(50.0, ingredient2.getLowThreshold());
		
		ingredient1.setLowThreshold(null);
		ingredient2 = entityManager.persistAndFlush(ingredient1);
		assertNull(ingredient2.getLowThreshold());
	}
}
