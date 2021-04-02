package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

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
			String json = "{\"name\" : \"Krishna\"}";

			Message<String> message = MessageBuilder.withPayload(json).setHeader("my-content-type", "application/json")
					.setHeader("my-origin", "localhost").build();

			gateway.print(message);
		};
	}

}