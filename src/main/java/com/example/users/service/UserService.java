package com.example.users.service;

import java.util.List;
import java.util.Optional;

import com.example.users.dto.RoleDTO;
import com.example.users.dto.UserDTO;
import com.example.users.model.User;

public interface UserService {
	public User saveUser(UserDTO userDto, Long roleId);
	
	public List <UserDTO> getAllUsers();
	
	public List <UserDTO> getUserById(Long userId);
	
	public User updateUser(UserDTO userDto, Long userId, Long roleId);
	
	public void deleteUser(UserDTO userDto);
	
	public List<UserDTO> getUsersOrderByName(int page, int rows);
	
}
