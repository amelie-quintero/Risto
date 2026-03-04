package com.example.Risto.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.Risto.entities.Dish;

public interface DishRepository extends CrudRepository<Dish, Integer> {

	public Optional<Dish> findByName(String name);
	public List<Dish> findByPriceBetween(Double lowerBound, Double upperBound);
	
	@Query("select d from d where d.name like %?1%")
	public List<Dish> findByNameLike(String search);
}
