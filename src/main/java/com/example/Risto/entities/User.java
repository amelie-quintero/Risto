package com.example.Risto.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String username;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String password;
	
	private Boolean active;
	
	@OneToMany(targetEntity = com.example.Risto.entities.Order.class,
			cascade = CascadeType.ALL,
			mappedBy = "id",
			orphanRemoval = true)
	private Set<Order> orders;
}
