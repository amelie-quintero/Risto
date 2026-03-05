package com.example.Risto.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.example.Risto.entities.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
	
	public Admin findByEmail(String email);
	public Admin findByUsername(String username);
	public Set<Admin> findByActiveTrue();
}
