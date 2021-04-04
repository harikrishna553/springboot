package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.BridgeFrom;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.SubscribableChannel;

import com.sample.app.gateway.CustomGateway;

@SpringBootApplication
@Configuration
public class App {
	@Bean(name = "pollableChannel")
	public PollableChannel pollableChannel() {
		return new QueueChannel();
	}

	@Bean(name = "pubSubChannel")
	@BridgeFrom(value = "pollableChannel", poller = @Poller(fixedDelay = "5000", maxMessagesPerPoll = "2"))
	public SubscribableChannel directChanel() {
		return new DirectChannel();
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
				Message<String> message = MessageBuilder.withPayload("Msg " + i).build();
				customGateway.print(message);
			}

		};
	}

}