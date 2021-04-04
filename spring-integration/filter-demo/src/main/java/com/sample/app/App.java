package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import com.sample.app.gateway.CustomGateway;

@SpringBootApplication
@Configuration
public class App {

	@Filter(inputChannel = "inputChannel", outputChannel = "secretChannel")
	boolean filter(Message<?> message) {
		String msg = message.getPayload().toString();

		return msg.contains("secret");
	}

	@Autowired
	private CustomGateway customGateway;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			for (int i = 0; i < 10; i++) {
				Message<?> message = null;
				if (i % 2 == 0) {
					message = MessageBuilder.withPayload("secret message " + i).setHeader("messageType", "secret")
							.build();
				} else if (i % 3 == 0) {
					message = MessageBuilder.withPayload("public message " + i).setHeader("messageType", "public")
							.build();
				} else {
					message = MessageBuilder.withPayload("Generic message " + i).setHeader("messageType", "public")
							.build();
				}

				customGateway.print(message);
			}

		};
	}

}