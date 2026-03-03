package com.example.Risto.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dishes")
public class Dish {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private String name;
	
	@NotNull
	private Double price;
	
	@OneToMany(targetEntity = com.example.Risto.entities.DishIngredient.class,
			cascade = CascadeType.ALL,
			mappedBy = "id",
			orphanRemoval = true)
	private Set<DishIngredient> dishIngredients;
	
	@OneToMany(targetEntity = com.example.Risto.entities.OrderDish.class,
			cascade = CascadeType.ALL, 
			mappedBy = "id", 
			orphanRemoval = true)
	private Set<OrderDish> orderDishes;
}
