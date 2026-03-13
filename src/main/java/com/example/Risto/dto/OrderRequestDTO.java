package com.example.Risto.dto;

import java.time.LocalDateTime;

import com.example.Risto.constants.OrderStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequestDTO {

	@NotNull
	private OrderStatus status;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
}
