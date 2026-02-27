package com.example.Risto.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Ingredients")
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String name;
	
	@PositiveOrZero
	private Double amount;
	
	private Set<Dish> dishes;
	
	@ManyToMany(targetEntity = com.example.Risto.entities.Dish.class, mappedBy = "ingredients")
	public Set<Dish> getDishes() {
		return dishes;
	}
}
