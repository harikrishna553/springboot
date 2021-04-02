package com.sample.app;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

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

			Map<String, Object> headers = new HashMap<>();
			headers.put("my-content-type", "application/json");
			headers.put("my-origin", "localhost");
			headers.put("my-content-encoding", "utf-8");

			Message<String> message = new GenericMessage<>(json, headers);

			gateway.print(message);
		};
	}

}
