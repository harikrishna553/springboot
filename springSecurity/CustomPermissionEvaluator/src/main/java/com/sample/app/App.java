package com.sample.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sample.app.entity.Role;
import com.sample.app.entity.User;
import com.sample.app.repository.UserRepository;

@SpringBootApplication
public class App implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role userRole = new Role();
		userRole.setRoleName("ROLE_USER");

		Role supportRole = new Role();
		supportRole.setRoleName("ROLE_SUPPORT");

		Role adminRole = new Role();
		adminRole.setRoleName("ROLE_ADMIN");

		User user1 = new User();
		user1.setUserName("krishna");
		user1.setPassword(passwordEncoder().encode("password123"));
		user1.setRoles(Arrays.asList(userRole, adminRole));

		User user2 = new User();
		user2.setUserName("rama");
		user2.setPassword(passwordEncoder().encode("password123"));
		user2.setRoles(Arrays.asList(supportRole));

		userRepository.save(user1);
		userRepository.save(user2);

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder(16);
	}

}
