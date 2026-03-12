package com.example.Risto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

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
	
	public User getActiveUser(int userId) {
		User user = getUser(userId);
		return !ObjectUtils.isEmpty(user) && user.getActive()
				? user
				: null;
	}
	
	public User getUser(int userId) {
		Optional<User> userOpt = this.userStore.findById(userId);
		User user = userOpt.orElse(null);
		return user;
	}
	
	public User getUserByEmail(String email) {
		Optional<User> userOpt = this.userStore.findByEmail(email);
		User user = userOpt.orElse(null);
		return user;
	}
	
	public User getUserByUsername(String username) {
		Optional<User> userOpt = this.userStore.findByUsername(username);
		User user = userOpt.orElse(null);
		return user;
	}
	
	private void setUserActive(User user, boolean active) {
		user.setActive(active);
		this.userStore.save(user);
	}
	
	public void activateUser(User user) {
		setUserActive(user, true);
	}
	
	public boolean activateUser(int userId) {
		Optional<User> userOpt = this.userStore.findById(userId);
		if (userOpt.isPresent()) {
			setUserActive(userOpt.get(), true);
			return true;
		}
		return false;
	}
	
	public void deactivateUser(User user) {
		setUserActive(user, false);
	}
	
	public boolean deactivateUser(int userId) {
		Optional<User> userOpt = this.userStore.findById(userId);
		if (userOpt.isPresent()) {
			setUserActive(userOpt.get(), false);
			return true;
		}
		return false;
	}
	
	public void deleteUser(User user) {
		this.userStore.delete(user);
	}
	
	public void deleteUser(int userId) {
		this.userStore.deleteById(userId);
	}
	
	public boolean userLogin(String username, String password) {
		return !userStore.findUserLogin(username, password).isEmpty();
	}
}
