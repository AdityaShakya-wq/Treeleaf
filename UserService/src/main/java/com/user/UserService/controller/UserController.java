package com.user.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.UserService.entity.Blogs;
import com.user.UserService.entity.Users;
import com.user.UserService.exceptions.UserNotFoundException;
import com.user.UserService.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/createUser")
	public ResponseEntity<Users> saveNewUser(@RequestBody Users user)
	{
		try
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		
	}
	
	@GetMapping("/allUsers")
	public ResponseEntity<List<Users>> getUsers()
	{
		try {
			return ResponseEntity.status(HttpStatus.FOUND).body(userService.getAllUsers());
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/{user-id}/blogs")
	public ResponseEntity<List<Blogs>> getUserBlogs(@PathVariable("user-id") String uid)
	{
		
		Users u = userService.getUserById(Long.parseLong(uid));
		
		if(u==null)
			throw new UserNotFoundException("Invalid user id");
		else
		{
			List<Blogs> blogsList = userService.getBlogsByUser(Long.parseLong(uid));
			return ResponseEntity.status(HttpStatus.OK).body(blogsList);
		}
	}
	
}
