package com.example.Risto.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.Risto.entities.Dish;

public interface DishRepository extends CrudRepository<Dish, Integer> {

	public Dish findDishByName(String name);
}
