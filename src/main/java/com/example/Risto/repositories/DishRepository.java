package com.example.Risto.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.Risto.entities.Dish;

public interface DishRepository extends CrudRepository<Dish, Integer> {

	public Dish findByName(String name);
	public List<Dish> findByPriceLessThan(Double price);
	public List<Dish> findByPriceGreaterThan(Double price);
}
