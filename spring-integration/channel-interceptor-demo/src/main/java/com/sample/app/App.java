package com.sample.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;

import com.sample.app.gateway.CustomGateway;
import com.sample.app.interceptor.CustomChannelInterceptor;

@SpringBootApplication
@Configuration
public class App {
	@Bean(name = "pollableChannel")
	public PollableChannel pollableChannel() {
		QueueChannel channel = new QueueChannel();
		channel.setInterceptors(Arrays.asList(new CustomChannelInterceptor()));
		return channel;
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
				Message<String> message = MessageBuilder.withPayload("secret message " + i).build();
				customGateway.print(message);
			}

		};
	}

}