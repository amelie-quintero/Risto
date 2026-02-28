package com.example.Risto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orderdishes")
public class OrderDish {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private int orderId;
	
	@NotNull
	private int dishId;
	
	private Order order;
	
	private Dish dish;
	
	@ManyToOne
	@JoinColumn(name = "orderId", nullable = false)
	public Order getOrder() { return order; }
	
	@ManyToOne
	@JoinColumn(name = "dishId", nullable = false)
	public Dish getDish() { return dish; }
}
