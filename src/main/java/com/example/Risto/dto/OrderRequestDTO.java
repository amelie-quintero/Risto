package com.example.Risto.dto;

import java.time.LocalDateTime;

import com.example.Risto.constants.OrderStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequestDTO {

	private OrderStatus status;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
}
