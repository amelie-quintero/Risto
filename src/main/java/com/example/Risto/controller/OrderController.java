package com.example.Risto.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Risto.dto.PlaceOrderRequestDTO;
import com.example.Risto.dto.PlaceOrderResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

	@PostMapping("/place")
	public PlaceOrderResponseDTO placeOrder(@Valid PlaceOrderRequestDTO data) {
		
		
		return PlaceOrderResponseDTO.builder().success(false).build();
	}
}
