package com.example.Risto.dto;

import java.util.List;
import lombok.Data;

@Data
public class PlaceOrderRequestDTO {

	private int userId;
	private List<PlaceOrderRequestDishDTO> dishes;
	
	@Data
	public class PlaceOrderRequestDishDTO {
		private int dishId;
		private String notes;
	}
}
