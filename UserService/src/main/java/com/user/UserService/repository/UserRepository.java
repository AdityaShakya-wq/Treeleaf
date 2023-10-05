package com.user.UserService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.UserService.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long>{
	
	public Optional<Users> findByUsername(String username);

}
