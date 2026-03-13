package com.example.Risto.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.example.Risto.constants.OrderStatus;
import com.example.Risto.dto.PlaceOrderRequestDTO.PlaceOrderRequestDishDTO;
import com.example.Risto.entities.Dish;
import com.example.Risto.entities.Order;
import com.example.Risto.entities.OrderDish;
import com.example.Risto.entities.User;
import com.example.Risto.exceptions.MissingIngredientException;
import com.example.Risto.repositories.OrderDishRepository;
import com.example.Risto.repositories.OrderRepository;

import jakarta.transaction.Transactional;

@Component
public class OrderService {
	
	@Autowired
	private OrderRepository orderStore;
	
	@Autowired
	private OrderDishRepository odStore;
	
	@Autowired
	private DishService dishService;
	
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
	
	public List<Order> getAllOrders() {
		return (List<Order>) orderStore.findAll();
	}
	
	public List<Order> getOrdersInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
		List<Order> orders = this.orderStore.findByDateBetween(startDate, endDate);
		return orders;
	}
	
	public List<Order> getOrdersWithStatus(OrderStatus status) {
		List<Order> orders = this.orderStore.findByStatus(status);
		return orders;
	}
	
	public List<Order> getOrdersInRangeWithStatus(OrderStatus status, LocalDateTime startDate, LocalDateTime endDate) {
		List<Order> orders = orderStore.findByStatusWithDateBetween(status, startDate, endDate);
		return orders;
	}
	
	@Transactional
	public Order createOrder(User user, List<PlaceOrderRequestDishDTO> dishes) {
		Order order = orderStore.save(Order.builder().date(LocalDateTime.now()).user(user).status(OrderStatus.PENDING).build());
		List<OrderDish> orderDishes = new ArrayList<OrderDish>();
		dishes.forEach(dishDto -> {
			Dish dish = dishService.getDishById(dishDto.getDishId());
			if (!ObjectUtils.isEmpty(dish)) {
				orderDishes.add(OrderDish.builder().dish(dish).order(order).notes(dishDto.getNotes()).build());
			}
		});
		if (!orderDishes.isEmpty()) {
			odStore.saveAll(orderDishes);
		}
		return order;
	}
	
	@Transactional(rollbackOn = {MissingIngredientException.class})
	public void processOrder(Order order, Boolean make) throws MissingIngredientException {
		if (Boolean.TRUE != make) make = Boolean.FALSE;
		
		if (order.getStatus() == OrderStatus.COMPLETED) {
			return;
		}
		
		Set<OrderDish> orderDishes = order.getOrderDishes();
		for (OrderDish od : orderDishes) {
			dishService.processDish(od.getDish(), make);
		}
		
		if (make) {
			order.setStatus(OrderStatus.COMPLETED);
		} else {
			order.setStatus(OrderStatus.INPROGRESS);
		}
		orderStore.save(order);
	}
}
