package com.example.Risto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Risto.entities.Dish;
import com.example.Risto.entities.Order;
import com.example.Risto.entities.OrderDish;
import com.example.Risto.repositories.OrderRepository;

@Component
public class OrderService {
	
	@Autowired
	private OrderRepository orderStore;
	
	public List<Order> getOrdersForUser(int userId) {
		List<Order> orders = this.orderStore.findByUserId(userId);
		return orders;
	}
	
	public List<Dish> getDishesInOrder(Order order) {
		Set<OrderDish> orderDishes = order.getOrderDishes();
		List<Dish> dishes = new ArrayList<Dish>();
		orderDishes.forEach(od -> {dishes.add(od.getDish());});
		return dishes;
	}
	
	public List<Dish> getDishesInOrder(int orderId) {
		Optional<Order> orderOpt = this.orderStore.findById(orderId);
		if (orderOpt.isPresent()) {
			return getDishesInOrder(orderOpt.get());
		}
		return List.of();
	}
	
	public void deleteOrder(Order order) {
		this.orderStore.delete(order);
	}
	
	public boolean deleteOrder(int orderId) {
		Optional<Order> orderOpt = this.orderStore.findById(orderId);
		if (orderOpt.isPresent()) {
			deleteOrder(orderOpt.get());
			return true;
		}
		return false;
	}
}
