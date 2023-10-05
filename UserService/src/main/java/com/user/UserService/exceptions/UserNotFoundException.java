package com.user.UserService.exceptions;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String msg)
	{
		super(msg);
	}

}
