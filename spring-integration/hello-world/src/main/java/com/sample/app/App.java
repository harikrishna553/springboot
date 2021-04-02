package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sample.app.endpoints.CustomGateway;

@SpringBootApplication
@Configuration
public class App {

	@Autowired
	private CustomGateway gateway;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			gateway.print("Hello World");
			gateway.reverse("Reverse Me!!!!");
		};
	}

}
