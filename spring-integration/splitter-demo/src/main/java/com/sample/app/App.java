package com.sample.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import com.sample.app.gateway.CustomGateway;

@SpringBootApplication
@Configuration
public class App {

	@Splitter(inputChannel = "inputChannel", outputChannel = "outputChannel")
	List<Message<String>> splitMessage(Message<?> message) {

		List<Message<String>> messages = new ArrayList<>();

		String[] msgSplits = message.getPayload().toString().split(",");

		for (String split : msgSplits) {
			Message<String> msg = MessageBuilder.withPayload(split).build();
			messages.add(msg);
		}

		return messages;
	}

	@Autowired
	private CustomGateway customGateway;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			Message<String> msg = MessageBuilder.withPayload("Cricket,Football,Tennis").build();

			customGateway.print(msg);
		};

	};

}