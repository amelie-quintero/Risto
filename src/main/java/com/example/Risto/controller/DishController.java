package com.example.Risto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Risto.dto.MenuResponseDTO;
import com.example.Risto.services.DishService;

@RestController
@RequestMapping(value = "/menu")
public class DishController {
	
	@Autowired
	private DishService dishService;
	
	@GetMapping(value = "/all")
	public MenuResponseDTO getFullMenu() {
		return MenuResponseDTO.builder()
				.dishes(dishService.getMenu(true))
				.build();
	}
	
	@GetMapping(value = "/instock")
	public MenuResponseDTO getInStockMenu() {
		return MenuResponseDTO.builder()
				.dishes(dishService.getMenu(false))
				.build();
	}
}
