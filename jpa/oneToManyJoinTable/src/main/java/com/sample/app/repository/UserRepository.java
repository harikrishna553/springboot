package com.sample.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.sample.app.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}