package com.sample.app;

import com.sample.app.model.User;
import com.sample.app.model.UserAuthorizations;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sample.app.repository.UserAuthorizationRepository;
import com.sample.app.repository.UserRepository;

@SpringBootApplication
public class App {
	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository,
			UserAuthorizationRepository userAuthorizationRepository) {
		return (args) -> {

			User user1 = new User();
			user1.setUserName("krishna");
			user1.setPassword(passwordEncoder().encode("password123"));

			User user2 = new User();
			user2.setUserName("ram");
			user2.setPassword(passwordEncoder().encode("ram123"));

			UserAuthorizations userAuthorization1 = new UserAuthorizations();
			userAuthorization1.setUserName("krishna");
			userAuthorization1.setAuthGroup("ROLE_ADMIN");

			UserAuthorizations userAuthorization2 = new UserAuthorizations();
			userAuthorization2.setUserName("ram");
			userAuthorization2.setAuthGroup("ROLE_USER");

			userRepository.saveAll(Arrays.asList(user1, user2));
			userAuthorizationRepository.saveAll(Arrays.asList(userAuthorization1, userAuthorization2));
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(16);
	}
}
