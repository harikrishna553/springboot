package com.sample.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Service Registry API", version = "1.0", description = "API for managing service registrations"))
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}