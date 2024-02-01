package com.ait.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	public User  findByEmail(String email);
	public User findByEmailAndPwd(String email, String pwd);
}
