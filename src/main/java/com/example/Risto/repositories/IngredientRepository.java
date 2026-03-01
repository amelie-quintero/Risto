package com.example.Risto.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.example.Risto.entities.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

	public Ingredient findIngredientByName(String name);
	public Set<Ingredient> findLowIngredients();
}
