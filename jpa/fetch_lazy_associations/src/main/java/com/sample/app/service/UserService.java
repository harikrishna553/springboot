package com.sample.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.app.model.User;
import com.sample.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public User findById(Integer id) {
		User user = userRepository.findById(id).get();
		// To load lazy association roles.
		user.getRoles().size();
		return user;
	}
	
	public User findByIdAndFetchRolesEagerly(Integer id) {
		User user = userRepository.findByIdAndFetchRolesEagerly(id);
		return user;
	}

}