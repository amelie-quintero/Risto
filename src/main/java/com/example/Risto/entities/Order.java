package com.example.Risto.entities;

import java.sql.Date;
import java.util.Set;

import com.example.Risto.constants.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	private int userId;
	
	private Date date;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@OneToMany(targetEntity = com.example.Risto.entities.OrderDish.class,
			cascade = CascadeType.ALL, 
			mappedBy = "id", 
			orphanRemoval = true)
	private Set<OrderDish> orderDishes;
	
	@ManyToOne
	@JoinColumn(name = "userid", nullable = false)
	private User user;
}
