package com.sample.app;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.integration.annotation.Poller;

@SpringBootApplication
@Configuration
public class App {

	@InboundChannelAdapter(channel = "printChannel", poller = { @Poller(maxMessagesPerPoll = "2", fixedRate = "5000") })
	Message<String> getMessageFromExternalSystem() {
		UUID uuid = UUID.randomUUID();
		String secretMsg = uuid.toString();

		return MessageBuilder.withPayload(secretMsg).build();

	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}