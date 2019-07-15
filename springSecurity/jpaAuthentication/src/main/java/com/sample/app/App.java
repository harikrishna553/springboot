package com.sample.app;

import com.sample.app.model.User;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.repository.UserRepository;

@SpringBootApplication
public class App {
	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository) {
		return (args) -> {

			User user1 = new User();
			user1.setUserName("krishna");
			user1.setPassword("password123");

			User user2 = new User();
			user2.setUserName("ram");
			user2.setPassword("ram123");

			userRepository.saveAll(Arrays.asList(user1, user2));
		};
	}
}

