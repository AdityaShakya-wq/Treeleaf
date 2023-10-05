package com.blog.service.exceptions;

public class NoBlogsFoundException extends RuntimeException{
	
	public NoBlogsFoundException(String msg)
	{
		super(msg);
	}

}
