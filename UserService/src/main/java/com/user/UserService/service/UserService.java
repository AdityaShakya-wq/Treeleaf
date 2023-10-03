package com.user.UserService.service;

import com.user.UserService.entity.Users;

public interface UserService {
	
	public Users getUser();
	
	public Users saveUser(Users user);

}
