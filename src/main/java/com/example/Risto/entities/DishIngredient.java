package com.example.Risto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dishingredients")
public class DishIngredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private int dishId;
	
	@NotNull
	private int ingredientId;
	
	@NotNull
	private Double amount;
	
	@ManyToOne
	@JoinColumn(name = "dishId", nullable = false)
	private Dish dish;
	
	@ManyToOne
	@JoinColumn(name = "ingredientId", nullable = false)
	private Ingredient ingredient;
}
