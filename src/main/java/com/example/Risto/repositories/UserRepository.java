package com.example.Risto.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.example.Risto.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findUserByEmail(String email);
	public User findUserByName(String name);
	public Set<User> getActiveUsers();
}
