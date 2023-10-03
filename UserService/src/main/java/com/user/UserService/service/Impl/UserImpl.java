package com.user.UserService.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.UserService.entity.Users;
import com.user.UserService.repository.UserRepository;
import com.user.UserService.service.UserService;

@Service
public class UserImpl implements UserService{
	
	@Autowired 
	UserRepository userRepo;

	@Override
	public Users getUser() {
		return null;
	}

	@Override
	public Users saveUser(Users user) {
		try
		{
			return userRepo.save(user);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

}
