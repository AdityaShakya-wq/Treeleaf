package com.user.UserService.service;

import java.util.List;

import com.user.UserService.entity.Blogs;
import com.user.UserService.entity.Users;

public interface UserService {
	
	public Users getUserById(Long id);
	
	public Users saveUser(Users user);
	
	public List<Users> getAllUsers();
	
	public List<Blogs> getBlogsByUser(Long uid);
	
	public String deleteABlogByUser(String uid, String bid);
	
	public String editABlogByUser(String uid, Blogs bid) throws Exception;

}
