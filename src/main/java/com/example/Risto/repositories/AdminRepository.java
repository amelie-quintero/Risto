package com.example.Risto.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.Risto.entities.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
	
	public Optional<Admin> findByUsername(String username);

	public List<Admin> findByActiveTrue();
}
