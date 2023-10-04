package com.blog.service.service;

import java.util.List;

import com.blog.service.entity.Blogs;

public interface BlogService {
	
	public List<Blogs> getAllBlogs();
	
	public Blogs getABlog(Long bid);
	
	public List<Blogs> getUserBlogs(Long uid);
	
	public Blogs createABlog(Blogs b) throws Exception;
	
	public void deleteABlog(Long bid);

}
