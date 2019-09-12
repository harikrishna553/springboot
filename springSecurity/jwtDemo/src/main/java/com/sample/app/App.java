package com.sample.app;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sample.app.entity.User;
import com.sample.app.model.Role;
import com.sample.app.service.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class App implements CommandLineRunner {

	@Autowired
	UserService userService;

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... params) throws Exception {
		User admin = new User();
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setEmail("admin@abc123.com");
		admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

		userService.signup(admin);

		User client = new User();
		client.setUsername("client");
		client.setPassword("client");
		client.setEmail("client@abc123.com");
		client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_USER)));

		userService.signup(client);
	}

}
