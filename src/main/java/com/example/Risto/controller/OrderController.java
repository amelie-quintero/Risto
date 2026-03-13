package com.example.Risto.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Risto.constants.Messages;
import com.example.Risto.constants.OrderStatus;
import com.example.Risto.dto.OrderRequestDTO;
import com.example.Risto.dto.OrderResponseDTO;
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
			return PlaceOrderResponseDTO.builder().success(true).orderId(order.getId()).message(Messages.Success.ORDER_SUCCESS).build();
		} else {
			message = Messages.Errors.NOT_AN_ACTIVE_USER;
		}
		return PlaceOrderResponseDTO.builder().success(false).message(message).build();
	}
	
	@GetMapping
	public OrderResponseDTO getOrders(@RequestParam String status, @RequestParam Optional<String> startDate, @RequestParam Optional<String> endDate){
		LocalDateTime start = null, end = null;
		if (startDate.isPresent()) {
			start = LocalDateTime.parse(startDate.get());
		}
		if (endDate.isPresent()) {
			end = LocalDateTime.parse(endDate.get());
		}
		return postOrders(OrderRequestDTO.builder().status(OrderStatus.valueOf(status)).startDate(start).endDate(end).build());
	}
	
	@PostMapping
	public OrderResponseDTO postOrders(@Valid OrderRequestDTO data) {
		List<Order> orders;
		if (ObjectUtils.isEmpty(data.getStartDate()) && ObjectUtils.isEmpty(data.getEndDate())) {
			if (ObjectUtils.isEmpty(data.getStatus())) {
				orders = orderService.getAllOrders();
			} else {
				orders = orderService.getOrdersWithStatus(data.getStatus());
			}
		} else {
			LocalDateTime startDate = ObjectUtils.isEmpty(data.getStartDate()) ? LocalDateTime.MIN : data.getStartDate();
			LocalDateTime endDate = ObjectUtils.isEmpty(data.getEndDate()) ? LocalDateTime.MAX : data.getEndDate();
			if (ObjectUtils.isEmpty(data.getStatus())) {
				orders = orderService.getOrdersInDateRange(startDate, endDate);
			} else {
				orders = orderService.getOrdersInRangeWithStatus(data.getStatus(), startDate, endDate);
			}
		}
		return OrderResponseDTO.builder()
				.success(true)
				.message("Success")
				.orders(orders)
				.build();
	}
}
