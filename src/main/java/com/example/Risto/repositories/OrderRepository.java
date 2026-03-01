package com.example.Risto.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.Risto.entities.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

	
}
