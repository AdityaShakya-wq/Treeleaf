package com.user.UserService.externalServices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.UserService.entity.Blogs;

@FeignClient(name="BLOG-SERVICE")
public interface BlogService {
	
	@GetMapping("/blogs/getBlogByUser/{uid}")
	List<Blogs> getBlogByUserId(@PathVariable("uid") String uid);

}
