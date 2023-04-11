package com.example.users.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.users.dto.RoleDTO;
import com.example.users.dto.UserDTO;
import com.example.users.exception.ResourceNotFoundException;
import com.example.users.model.Email;
import com.example.users.model.Role;
import com.example.users.model.User;
import com.example.users.repository.RoleRepository;
import com.example.users.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public User saveUser(UserDTO userDto, Long roleId) {
		User newUser = new User();
		Role existingRole = roleRepository.findById(roleId).orElseThrow(() ->
		new ResourceNotFoundException("Role", "Id", roleId));
		
		existingRole.setRoleId(roleId);
		newUser.setUserName(userDto.name());
		
		Set <Role> setExistingRole = new HashSet<>();
		setExistingRole.add(existingRole);
		
		newUser.setRole(setExistingRole);
		
		return userRepository.save(newUser);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		return userRepository.findAll()
				.stream()
				.map(user -> new UserDTO(
						user.getUserId(),
						user.getUserName(),
						user.getRole()
							.stream()
							.map(Role::getRoleName)
							.collect(Collectors.toList()),
						user.getEmail()
							.stream()
							.map(Email::getEmailAddress)
							.collect(Collectors.toList())
						))
						.collect(Collectors.toList());
	}

	@Override
	public List<UserDTO> getUserById(Long id) {
		User existingUser = userRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("User", "Id", id));
		
		return userRepository.findById(id)
				.stream()
				.map(user -> new UserDTO(
						user.getUserId(),
						user.getUserName(),
						user.getRole()
							.stream()
							.map(Role::getRoleName)
							.collect(Collectors.toList()),
						user.getEmail()
							.stream()
							.map(Email::getEmailAddress)
							.collect(Collectors.toList())
						))
						.collect(Collectors.toList());	
	}

	@Override
	public User updateUser(UserDTO userDto, Long userId, Long roleId) {
		User existingUser = userRepository.findById(userId).orElseThrow(() ->
		new ResourceNotFoundException("User", "Id", userId));
		
		Role existingRole = roleRepository.findById(roleId).orElseThrow(() ->
		new ResourceNotFoundException("Role", "Id", roleId));
		
		existingUser.setUserName(userDto.name());
		
		existingRole.setRoleId(roleId);
		existingRole.getRoleName();
		
		Set <Role> setExistingRole = new HashSet<>();
		setExistingRole.add(existingRole);
		
		existingUser.setRole(setExistingRole);
		
		userRepository.save(existingUser);
		
		return existingUser;
	}

	@Override
	public void deleteUser(UserDTO userDto) {
		User existingUser = userRepository.findById(userDto.id()).orElseThrow(() ->
		new ResourceNotFoundException("User", "Id", userDto.id()));
		
		existingUser.setUserId(userDto.id());
		
		userRepository.deleteById(userDto.id());
	}

	@Override
	public List<UserDTO> getUsersOrderByName(int page, int rows) {
		Pageable list = PageRequest.of(page, rows);
		
		return userRepository.findByOrderByUserName(list)
				.stream()
				.map(user -> new UserDTO(
						user.getUserId(),
						user.getUserName(),
						user.getRole()
							.stream()
							.map(Role::getRoleName)
							.collect(Collectors.toList()),
						user.getEmail()
							.stream()
							.map(Email::getEmailAddress)
							.collect(Collectors.toList())
						))
						.collect(Collectors.toList());
	}
	
	

}
