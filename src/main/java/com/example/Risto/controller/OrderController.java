package com.example.Risto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Risto.constants.Messages;
import com.example.Risto.dto.PlaceOrderRequestDTO;
import com.example.Risto.dto.PlaceOrderResponseDTO;
import com.example.Risto.entities.Order;
import com.example.Risto.entities.User;
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
		User user = userService.getActiveUser(data.getUserId());
		if (!ObjectUtils.isEmpty(user)) {
			Order order = orderService.createOrder(user, data.getDishes());
			return PlaceOrderResponseDTO.builder().success(true).message(Messages.Success.ORDER_SUCCESS).build();
		} else {
			message = Messages.Errors.NOT_AN_ACTIVE_USER;
		}
		return PlaceOrderResponseDTO.builder().success(false).message(message).build();
	}
}
