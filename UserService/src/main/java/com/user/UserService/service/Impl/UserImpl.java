package com.user.UserService.service.Impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.UserService.entity.Blogs;
import com.user.UserService.entity.Users;
import com.user.UserService.repository.UserRepository;
import com.user.UserService.service.UserService;

@Service
public class UserImpl implements UserService{
	
	@Autowired 
	UserRepository userRepo;
	
	@Autowired
	RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(UserService.class);
	@Override
	public Users getUserById(Long uid) {
		try
		{
			Optional<Users> user=userRepo.findById(uid);
			
			if(user.isPresent())
				return user.get();
			else
				return null;	
			
		}
		catch(Exception ex)
		{
			return null;
		}
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

	@Override
	public List<Users> getAllUsers() {
		try
		{
			List<Users> userlist =  userRepo.findAll();
			
			List<Users> resultUserList = userlist.stream().map(ul->{
				//calling blog service to fetch every user's blogs
				try
				{
					ResponseEntity<List> responseBlogList = restTemplate.getForEntity("http://BLOG-SERVICE/blogs/getBlogByUser/"+ul.getUserId(), List.class);
					ul.setBlogsList(responseBlogList.getBody());
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				
				
				return ul;
			}).collect(Collectors.toList());
			
			return resultUserList;
			
		}
		catch(Exception ex)
		{
			return null;
		}
	}

	@Override
	public List<Blogs> getBlogsByUser(Long uid) {
		//calling api in blogService to fetch blogs posted by a particular user
		ResponseEntity<ArrayList> responseObject = restTemplate.getForEntity("http://BLOG-SERVICE/blogs/getBlogByUser/"+uid, ArrayList.class);
		logger.info("{}",responseObject);
		return responseObject.getBody();
	}

	@Override
	public String deleteABlogByUser(String uid, String bid) {
		ResponseEntity<String> result = restTemplate.exchange("http://BLOG-SERVICE/blogs/"+uid+"/deleteABlog/"+bid,HttpMethod.DELETE, null, String.class);
		logger.info("{}",result.getBody());
		return result.getBody();
	}

	@Override
	public String editABlogByUser(String uid, Blogs b) throws Exception {
		ObjectMapper om = new ObjectMapper();
		String requestBody = om.writeValueAsString(b);
		System.out.println(requestBody);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(requestBody,headers);
		ResponseEntity<String> result = restTemplate.exchange("http://BLOG-SERVICE/blogs/"+uid+"/editABlog", HttpMethod.PUT, entity,String.class);
		return result.getBody();
	}

}
