package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import com.sample.app.gateway.CustomGateway;

@SpringBootApplication
@Configuration
public class App {

	@Transformer(inputChannel = "inputChannel", outputChannel = "outputChannel")
	public Message<String> convertToUppercase(Message<String> message) {

		String payload = message.getPayload();

		Message<String> messageInUppercase = MessageBuilder.withPayload(payload.toUpperCase()).build();

		return messageInUppercase;

	}

	@Autowired
	private CustomGateway customGateway;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			Message<String> msg = MessageBuilder.withPayload("i am in small letters, but will print in capital letters")
					.build();

			customGateway.print(msg);
		};

	};

}
