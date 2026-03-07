package com.example.Risto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
public class DishIngredientTests {
	
	@Autowired
	private TestEntityManager entityManager;
	
	private Dish dish1, dish2;
	
	private Ingredient ingredient1, ingredient2;
	
	private DishIngredient di;
	
	@BeforeEach
	void setup() {
		dish1 = entityManager.persist(Dish.builder().name("dish1").price(10.0).build());
		dish2 = entityManager.persist(Dish.builder().name("dish2").price(12.0).build());
		ingredient1 = entityManager.persist(Ingredient.builder().name("ing1").amount(100.0).lowThreshold(10.0).build());
		ingredient2 = entityManager.persist(Ingredient.builder().name("ing2").amount(100.0).lowThreshold(10.0).build());
		di = entityManager.persistAndFlush(DishIngredient.builder().dish(dish1).ingredient(ingredient1).amount(10.0).build());
	}
	
	@Test
	@DisplayName(value = "Test that the id field is populated correctly on object creation")
	void testId() {
		assertNotEquals(0, di.getId());
	}
	
	@Test
	@DisplayName(value = "Test that the created_at and updated_at fields are set correctly by auditor")
	void testAuditing() {
		assertNotNull(di.getCreatedAt());
		assertNotNull(di.getUpdatedAt());
		assertEquals(di.getCreatedAt(), di.getUpdatedAt());
		
		di.setDish(dish2);
		DishIngredient di1 = entityManager.persistAndFlush(di);
		assertTrue(di1.getUpdatedAt().isAfter(di1.getCreatedAt()));
	}
	
	@Test
	@DisplayName(value = "Test that the amount field is written and read correctly")
	void testAmount() {
		di.setAmount(15.0);
		DishIngredient di1 = entityManager.persistAndFlush(di);
		assertEquals(15.0, di1.getAmount());
	}
	
	@Test
	@DisplayName(value = "Test that the Dish value is written correctly and the dish_id column in the db is correct")
	void testDish() {
		di.setDish(dish2);
		DishIngredient di1 = entityManager.persistAndFlush(di);
		assertEquals(dish2, di1.getDish());
	}
	
	@Test
	@DisplayName(value = "Test that the Ingredient value is written correctly and the ingredient_id column in the db is correct")
	void testIngredient() {
		di.setIngredient(ingredient2);
		DishIngredient di1 = entityManager.persistAndFlush(di);
		assertEquals(ingredient2, di1.getIngredient());
	}
}
