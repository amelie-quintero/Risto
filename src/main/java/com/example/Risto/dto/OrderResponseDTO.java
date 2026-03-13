package com.example.Risto.dto;

import java.util.List;

import com.example.Risto.entities.Order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponseDTO {

	private boolean success;
	
	private String message;
	
	private List<Order> orders;
}
