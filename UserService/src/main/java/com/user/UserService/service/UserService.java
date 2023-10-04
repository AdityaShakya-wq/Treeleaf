package com.user.UserService.service;

import java.util.List;

import com.user.UserService.entity.Blogs;
import com.user.UserService.entity.Users;

public interface UserService {
	
	public Users getUserById(Long id);
	
	public Users saveUser(Users user);
	
	public List<Users> getAllUsers();
	
	public List<Blogs> getBlogsByUser(Long uid);

}
