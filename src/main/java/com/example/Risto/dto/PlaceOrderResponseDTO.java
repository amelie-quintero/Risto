package com.example.Risto.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceOrderResponseDTO {
	private boolean success;
	private int orderId;
	private LocalDateTime estimatedReadyAt;
	private String message;
}
