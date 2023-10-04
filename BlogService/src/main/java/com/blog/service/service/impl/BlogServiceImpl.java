package com.blog.service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.service.entity.Blogs;
import com.blog.service.repo.BlogRepository;
import com.blog.service.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	BlogRepository blogRepo;
	@Override
	public List<Blogs> getAllBlogs() {
		
		try {
			return blogRepo.findAll();
		}
		catch(Exception ex)
		{
			return null;
		}
	}

	@Override
	public Blogs getABlog(Long bid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Blogs> getUserBlogs(Long uid) {
		try
		{
			List<Blogs> blogsList = blogRepo.findAll();
			
			if(blogsList.size()>0)
			{
				blogsList = blogsList.stream().filter(bl->bl.getUserId().equals(uid)).collect(Collectors.toList());
				
				return blogsList;
			}
			else
			{
				return null;
			}
		}catch(Exception ex)
		{
			return null;
		}
	}

	@Override
	public Blogs createABlog(Blogs b) throws Exception {
		try {
			return blogRepo.save(b);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			throw new Exception("Failed");
		}
	}

}
