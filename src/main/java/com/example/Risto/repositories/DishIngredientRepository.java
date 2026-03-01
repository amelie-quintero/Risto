package com.example.Risto.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.Risto.entities.DishIngredient;

public interface DishIngredientRepository extends CrudRepository<DishIngredient, Integer> {

}
