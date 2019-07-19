package com.sample.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(App.class);

		ConfigurableEnvironment environment = new StandardEnvironment();
		environment.setActiveProfiles("dev", "prod");

		application.setEnvironment(environment);

		application.run(args);
	}

}
