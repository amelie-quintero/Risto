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
import com.example.Risto.entities.Order;
import com.example.Risto.entities.OrderDish;
import com.example.Risto.exceptions.MissingIngredientException;
import com.example.Risto.repositories.DishRepository;
import com.example.Risto.repositories.IngredientRepository;

import jakarta.transaction.Transactional;

@Component
public class DishService {
	
	@Autowired
	private DishRepository dishStore;
	
	@Autowired
	private IngredientRepository ingredientStore;
	
	public Dish getDishById(int id) {
		Optional<Dish> dishOpt = dishStore.findById(id);
		return dishOpt.orElse(null);
	}
	
	public Dish getDishByName(String name) {
		Optional<Dish> dishOpt = this.dishStore.findByName(name);
		Dish dish = dishOpt.get();
		return dish;
	}
	
	public List<Dish> searchDishes(String search) {
		List<Dish> dishes = this.dishStore.findByNameLike("%" + search + "%");
		return dishes;
	}
	
	public List<Dish> searchByPrice(Double lowerBound, Double upperBound) {
		List<Dish> dishes = this.dishStore.findByPriceBetween(lowerBound, upperBound);
		return dishes;
	}
	
	public List<Ingredient> getIngredientsInDish(Dish dish) {
		Set<DishIngredient> dishIngredients = dish.getDishIngredients();
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		dishIngredients.forEach(di -> {ingredients.add(di.getIngredient());});
		return ingredients;
	}
	
	public List<Order> getOrdersWithDish(Dish dish) {
		Set<OrderDish> orderDishes = dish.getOrderDishes();
		List<Order> orders = new ArrayList<Order>();
		orderDishes.forEach(od -> {orders.add(od.getOrder());});
		return orders;
	}
	
	@Transactional(rollbackOn = {MissingIngredientException.class})
	public void processDish(Dish dish, Boolean make) throws MissingIngredientException {
		if (Boolean.TRUE != make) make = Boolean.FALSE; //set default value
		
		Set<DishIngredient> dishIngredients = dish.getDishIngredients();
		List<String> missingIng = new ArrayList<String>();
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		for (DishIngredient di : dishIngredients) {
			Ingredient i = di.getIngredient();
			Double newAmount = i.getAmount() - di.getAmount();
			if (newAmount < Double.valueOf(0.0)) {
				missingIng.add(i.getName());
			}
			if (make) {
				i.setAmount(newAmount);
			}
			ingredients.add(i);
		}
		if (missingIng.size() > 0) {
			throw new MissingIngredientException(missingIng);
		}
		if (make) {
			ingredientStore.saveAll(ingredients);
		}
	}
}
