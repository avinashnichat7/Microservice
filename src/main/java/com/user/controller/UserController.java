package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.User;
import com.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<?> createUser(@RequestBody User user) {

		User saveUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
	}

	@GetMapping(value = "/allUser",produces = MediaType.APPLICATION_ATOM_XML_VALUE)
	public ResponseEntity<List<User>> findAllUser() {

		List<User> alluser = userService.getAlluser();

		return ResponseEntity.status(HttpStatus.OK).body(alluser);

	}

	@GetMapping("/{id}")
	public ResponseEntity<User> UserById(@PathVariable  String id) {

		User userById = userService.getUserById(id);

		return ResponseEntity.status(HttpStatus.OK).body(userById);
	}

	@DeleteMapping("/{id}" )
	public void deleteUser(@PathVariable String id) {

		userService.deleteUser(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String id){
		
		User updateUser = userService.updateUser(user, id);
		
		return ResponseEntity.status(HttpStatus.OK).body(updateUser);
		
	}
}
