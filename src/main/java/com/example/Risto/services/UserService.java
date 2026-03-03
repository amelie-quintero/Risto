package com.example.Risto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Risto.entities.User;
import com.example.Risto.repositories.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository userStore;
	
	public List<User> getAllUsers() {
		List<User> users = (List<User>) this.userStore.findAll();
		return users;
	}
	
	public List<User> getActiveUsers() {
		List<User> users = this.userStore.findByActive(true);
		return users;
	}
	
	public List<User> getInactiveUsers() {
		List<User> users = this.userStore.findByActive(false);
		return users;
	}
	
	public User getUser(int id) {
		Optional<User> userOpt = this.userStore.findById(id);
		User user = userOpt.get();
		return user;
	}
	
	public User getUserByEmail(String email) {
		Optional<User> userOpt = this.userStore.findByEmail(email);
		User user = userOpt.get();
		return user;
	}
	
	public User getUserByUsername(String name) {
		Optional<User> userOpt = this.userStore.findByUsername(name);
		User user = userOpt.get();
		return user;
	}
	
	private void setUserActive(User user, boolean active) {
		user.setActive(active);
		this.userStore.save(user);
	}
	
	public void activateUser(User user) {
		setUserActive(user, true);
	}
	
	public boolean activateUserById(int id) {
		Optional<User> userOpt = this.userStore.findById(id);
		if (userOpt.isPresent()) {
			setUserActive(userOpt.get(), true);
			return true;
		}
		return false;
	}
	
	public void deactivateUser(User user) {
		setUserActive(user, false);
	}
	
	public boolean deactivateUserById(int id) {
		Optional<User> userOpt = this.userStore.findById(id);
		if (userOpt.isPresent()) {
			setUserActive(userOpt.get(), false);
			return true;
		}
		return false;
	}
}
