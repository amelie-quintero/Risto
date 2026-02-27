package com.example.Risto.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
	
	private Set<Ingredient> ingredients;
	
	@ManyToMany(targetEntity = com.example.Risto.entities.Ingredient.class)
	public Set<Ingredient> getIngredients() {
		return ingredients;
	}
}
