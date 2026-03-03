package com.example.Risto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Risto.repositories.DishRepository;

@Component
public class DishService {
	
	@Autowired
	private DishRepository dishStore;
	
	
}
