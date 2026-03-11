package com.example.Risto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Risto.dto.LoginRequestDto;
import com.example.Risto.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public boolean userLogin(@Valid LoginRequestDto data) {
		return userService.userLogin(data.getUsername(), data.getPassword());
	}
}
