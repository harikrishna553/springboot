package com.sample.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sample.app.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = :id")
	public User findByIdAndFetchRolesEagerly(@Param("id") Integer id);

}