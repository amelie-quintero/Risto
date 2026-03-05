package com.example.Risto.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.CrudRepository;

import com.example.Risto.entities.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

	public Optional<Ingredient> findByName(String name);
	
	@NativeQuery("SELECT * FROM ingredients WHERE ingredients.name LIKE ?1")
	public List<Ingredient> findByNameLike(String search);
	
	@NativeQuery("SELECT * FROM ingredients WHERE ingredients.amound < ingredients.low_threshold")
	public List<Ingredient> findLowIngredients();
}
