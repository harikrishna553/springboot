package com.sample.app;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import com.sample.app.model.Role;
import com.sample.app.model.User;
import com.sample.app.repository.UserRepository;
import com.sample.app.service.UserService;

@SpringBootApplication
public class App {

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner demo(UserRepository userRepository, UserService userService) {
		return (args) -> {
			User user = new User();

			user.setFirstName("Krishna");
			user.setLastName("Gurram");

			Role role1 = new Role();
			role1.setRoleName("Admin");

			Role role2 = new Role();
			role2.setRoleName("Service_user");

			Set<Role> roles = new HashSet<>();
			roles.add(role1);
			roles.add(role2);

			user.setRoles(roles);

			User savedUser = userRepository.save(user);
			
			savedUser = userService.findById(savedUser.getId());
			System.out.println(savedUser);
			
			savedUser = userService.findByIdAndFetchRolesEagerly(savedUser.getId());
			System.out.println(savedUser);
		};
	}

}