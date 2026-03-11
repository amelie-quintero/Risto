package com.example.Risto.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.Risto.entities.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
	
	public Optional<Admin> findByUsername(String username);

	public List<Admin> findByActiveTrue();
	
	@Query("select A from admin A where A.username=?1 and A.password=?2 and A.active=true")
	public List<Admin> findAdminLogin(String username, String password);
}
