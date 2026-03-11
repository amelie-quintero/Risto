package com.example.Risto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Risto.dto.PlaceOrderRequestDTO;
import com.example.Risto.dto.PlaceOrderResponseDTO;
import com.example.Risto.services.OrderService;
import com.example.Risto.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;

	@PostMapping("/place")
	public PlaceOrderResponseDTO placeOrder(@Valid PlaceOrderRequestDTO data) {
		String message = "";
		if (userService.isActiveUser(data.getUserId())) {
			
		} else {
			message = "Not an active user.";
		}
		return PlaceOrderResponseDTO.builder().success(false).message(message).build();
	}
}
