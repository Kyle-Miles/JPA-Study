package com.example.users.service;

import java.util.List;

import com.example.users.model.User;

public interface UserService {
	public User saveUser(User user);
	
	public List <User> getAllUsers();

}
