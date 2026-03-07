package com.example.Risto.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Ingredients")
public class Ingredient extends AuditedEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String name;
	
	@NotNull
	@PositiveOrZero
	private Double amount;
	
	@PositiveOrZero
	@Column(name = "low_threshold")
	private Double lowThreshold;
	
	@OneToMany(targetEntity = com.example.Risto.entities.DishIngredient.class,
			cascade = CascadeType.ALL,
			mappedBy = "id",
			orphanRemoval = true)
	private Set<DishIngredient> dishIngredients;
}
