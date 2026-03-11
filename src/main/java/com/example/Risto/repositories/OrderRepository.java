package com.example.Risto.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.Risto.constants.OrderStatus;
import com.example.Risto.entities.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

	public List<Order> findByUserId(int userId);
	public List<Order> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	public List<Order> findByStatus(OrderStatus status);
}
