package com.sample.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sample.app.entity.User;
import com.sample.app.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}

		UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(username)
				.password(user.getPassword()).authorities(user.getRoles()).accountExpired(false).accountLocked(false)
				.credentialsExpired(false).disabled(false).build();
		return userDetails;
	}

}
