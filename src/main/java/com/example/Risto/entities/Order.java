package com.example.Risto.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Set<OrderDish> orderDishes;
	
	@OneToMany(targetEntity = com.example.Risto.entities.OrderDish.class,
			cascade = CascadeType.ALL, 
			mappedBy = "id", 
			orphanRemoval = true)
	public Set<OrderDish> orderDishes() { return orderDishes; };
}
