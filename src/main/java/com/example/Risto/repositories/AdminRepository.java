package com.example.Risto.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.Risto.entities.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
	
	public Admin findAdminByEmail(String email);
}
