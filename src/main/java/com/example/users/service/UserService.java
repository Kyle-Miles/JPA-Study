package com.example.users.service;

import java.util.List;
import java.util.Optional;

import com.example.users.model.User;

public interface UserService {
	public User saveUser(User user);
	
	public List <User> getAllUsers();
	
	public User getUserById(Long id);
	
	public User updateUser(User user, Long id);
	
	public void deleteUser(Long id);
	
	public Optional<List<User>> getUsersOrderByName(int page, int rows);
	
}
