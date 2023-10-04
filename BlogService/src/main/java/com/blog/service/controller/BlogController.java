package com.blog.service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.blog.service.entity.Blogs;
import com.blog.service.exceptions.NoBlogsFoundException;
import com.blog.service.service.BlogService;

@RestController
@RequestMapping("/blogs")
public class BlogController {
	
	@Autowired
	BlogService blogService;
	
	//api to create a blog
	@PostMapping(value="/createBlog")
	public ResponseEntity<Blogs> createBlog(@RequestBody Blogs blog)
	{
		try
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(blogService.createABlog(blog)); 
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@GetMapping("/getBlogs")
	public ResponseEntity<List<Blogs>> getBlogs()
	{
		List<Blogs> blogList = null;
		
		try
		{
			blogList = blogService.getAllBlogs();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		if(blogList== null || blogList.isEmpty())
			throw new NoBlogsFoundException("No blogs at the moment");
		else
			return ResponseEntity.status(HttpStatus.OK).body(blogList);
	}
	
	@GetMapping("/getBlogByUser/{uid}")
	public ResponseEntity<List<Blogs>> getBlogByUserId(@PathVariable("uid") String uid)
	{
		try {
			return ResponseEntity.status(HttpStatus.OK).body(blogService.getUserBlogs(Long.parseLong(uid)));
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	//delete a blog of a user
	@DeleteMapping("/{uid}/deleteABlog/{bid}")
	public String deleteBlog(@PathVariable("uid") String uid, @PathVariable("bid") String bid)
	{
		//get all the blogs that belongs to the user
		List<Blogs> userBlogLists = blogService.getUserBlogs(Long.parseLong(uid)); 
		
		//success - blog belongs to the user - hence delete the blog
		//failed - blog doesn't belong to the user - delete failed
		if(userBlogLists == null)
		{
			return "failed";
		}else
		{
			Optional<Blogs> b = userBlogLists.stream().filter(u->u.getBlogId().equals(Long.parseLong(bid))).findFirst();
			
			if(b.isPresent())
			{
				blogService.deleteABlog(b.get().getBlogId());
				return "success";
			}else
			{
				return "failed";
			}
		}
		
	}
	
	@PutMapping("/{uid}/editABlog")
	public String editABlog(@PathVariable("uid") String uid, @RequestBody Blogs eb) throws Exception
	{
		//get all the blogs that belongs to the user
		List<Blogs> userBlogLists = blogService.getUserBlogs(Long.parseLong(uid)); 
		
		//success - blog belongs to the user - hence edit the blog
		//failed - blog doesn't belong to the user - do not edit
		if(userBlogLists == null)
		{
			return "failed";
		}else
		{
			Optional<Blogs> b = userBlogLists.stream().filter(u->u.getBlogId().equals(eb.getBlogId())).findFirst();
			
			if(b.isPresent())
			{
				Blogs blog = b.get();
				blog.setBlogTitle(eb.getBlogTitle());
				blog.setBlogBody(eb.getBlogBody());
				blog.setBlogCategory(eb.getBlogCategory());
				blogService.createABlog(blog);
				return "success";
			}else
			{
				return "failed";
			}
		}
	}
}