package com.example.Risto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Risto.entities.Admin;
import com.example.Risto.repositories.AdminRepository;

public class AdminService {

	@Autowired
	private AdminRepository adminStore;
	
	public List<Admin> getAllAdmins() {
		return (List<Admin>) adminStore.findAll();
	}
	
	public List<Admin> getAllActiveAdmins() {
		return (List<Admin>) adminStore.findByActiveTrue();
	}
	
	public Admin getAdminFromUsername(String username) {
		Optional<Admin> adminOpt = adminStore.findByUsername(username);
		return adminOpt.orElse(null);
	}
	
	public boolean adminLogin(String username, String password) {
		return !adminStore.findAdminLogin(username, password).isEmpty();
	}
}
