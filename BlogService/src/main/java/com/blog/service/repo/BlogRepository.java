package com.blog.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.service.entity.Blogs;

public interface BlogRepository extends JpaRepository<Blogs, Long> {

}
