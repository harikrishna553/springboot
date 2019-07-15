package com.sample.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String userName);
}
