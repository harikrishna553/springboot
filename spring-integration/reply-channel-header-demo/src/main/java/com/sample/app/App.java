package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import com.sample.app.gateway.CustomGateway;

@SpringBootApplication
@Configuration
public class App {

	@Autowired
	private CustomGateway customGateway;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			Message<String> msg = MessageBuilder.withPayload("india is a beautiful country")
					.setHeader("replyChannel", "myOutputChannel").build();

			customGateway.print(msg);
		};

	};

}