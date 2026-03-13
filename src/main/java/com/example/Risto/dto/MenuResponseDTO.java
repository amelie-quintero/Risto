package com.example.Risto.dto;

import java.util.List;

import com.example.Risto.entities.Dish;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuResponseDTO {

	List<Dish> dishes;
}
