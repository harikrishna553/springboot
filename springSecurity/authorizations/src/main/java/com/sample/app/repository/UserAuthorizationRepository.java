package com.sample.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.model.UserAuthorizations;

public interface UserAuthorizationRepository extends JpaRepository<UserAuthorizations, Integer>{

	List<UserAuthorizations> findByUserName(String userName);
}
