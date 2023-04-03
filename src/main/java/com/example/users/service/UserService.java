package com.example.users.service;

import java.util.List;
import java.util.Optional;

import com.example.users.model.User;

public interface UserService {
	public User saveUser(User user);
	
	public List <User> getAllUsers();
	
}
