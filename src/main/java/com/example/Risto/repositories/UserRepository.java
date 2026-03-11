package com.example.Risto.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.Risto.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public Optional<User> findByEmail(String email);
	public Optional<User> findByUsername(String name);
	public List<User> findByActive(boolean active);
	
	@Query("select U from user U where U.username=?1 and U.password=?2")
	public List<User> findUserLogin(String username, String password);
}
