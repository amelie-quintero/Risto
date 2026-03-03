package com.example.Risto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Risto.entities.Dish;
import com.example.Risto.entities.DishIngredient;
import com.example.Risto.entities.Ingredient;
import com.example.Risto.repositories.IngredientRepository;

@Component
public class IngredientService {
	
	@Autowired
	private IngredientRepository ingredientStore;
	
	public Ingredient getIngredientById(int ingredientId) {
		Optional<Ingredient> ingredientOpt = this.ingredientStore.findById(ingredientId);
		Ingredient ingredient = ingredientOpt.get();
		return ingredient;
	}
	
	public Ingredient getIngredientByName(String name) {
		Optional<Ingredient> ingredientOpt = this.ingredientStore.findByName(name);
		Ingredient ingredient = ingredientOpt.get();
		return ingredient;
	}
	
	public List<Ingredient> searchIngredientsByName(String search) {
		List<Ingredient> ingredients = this.ingredientStore.findByNameLike(search);
		return ingredients;
	}
	
	public List<Ingredient> getLowIngredients() {
		List<Ingredient> ingredients = this.ingredientStore.findLowIngredients();
		return ingredients;
	}
	
	public List<Dish> getDishesWithIngredient(Ingredient ingredient) {
		Set<DishIngredient> dishIngredients = ingredient.getDishIngredients();
		List<Dish> dishes = new ArrayList<Dish>();
		dishIngredients.forEach(di -> {dishes.add(di.getDish());});
		return dishes;
	}
}
