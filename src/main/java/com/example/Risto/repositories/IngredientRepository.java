package com.example.Risto.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.Risto.entities.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

	public Optional<Ingredient> findByName(String name);
	
	@Query("select i from i where i.name like %?1%")
	public List<Ingredient> findByNameLike(String search);
	
	@Query("select i from i where i.amount < i.lowThreshold")
	public List<Ingredient> findLowIngredients();
}
