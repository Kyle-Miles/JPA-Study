package com.example.users.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.users.model.User;
import com.example.users.service.UserService;

@RestController
@RequestMapping("/users/user")
public class UserController {

	private UserService userService;
	
	@Autowired
	private UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping()
	public ResponseEntity<User> saveUser(
			@RequestBody User user) {
		return new ResponseEntity<User>(
				userService.saveUser(user),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		return new ResponseEntity<List<User>>(
				userService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("{user_id}")
	public ResponseEntity<User> getUserById(
			@PathVariable("user_id") Long id) {
		return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
	}
	
	@PutMapping("{user_id}")
	public ResponseEntity<User> updateUser(
			@PathVariable("user_id") Long id,
			@RequestBody User user) {
		return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
	}
	
	@DeleteMapping("{user_id}")
	public ResponseEntity<String> deleteUser(
			@PathVariable("user_id") Long id) {
		userService.deleteUser(id);
		return new ResponseEntity<String>("User " + id + " has been deleted...", HttpStatus.OK);
	}
	
	@GetMapping("/pages")
	public ResponseEntity<Optional<List<User>>> getUsersOrderByName(
			int page, int rows) {
		return new ResponseEntity<Optional<List<User>>>(userService.getUsersOrderByName(page-1, rows), HttpStatus.OK);
	}
	
}
