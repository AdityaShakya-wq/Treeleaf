package com.user.UserService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.UserService.entity.Users;
import com.user.UserService.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/save")
	public ResponseEntity<Users> saveNewUser(@RequestBody Users user)
	{
		Users u = userService.saveUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
}
