package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

@SpringBootApplication
@Configuration
public class App {

	@Autowired
	@Qualifier("echoChannel")
	private DirectChannel messageChannel;

	@Autowired
	@Qualifier("reverseEchoChannel")
	private DirectChannel reverseMessageChannel;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean(name = "echoChannel")
	public DirectChannel channel1() {
		DirectChannel channel = new DirectChannel();
		return channel;
	}

	@Bean(name = "reverseEchoChannel")
	public DirectChannel channel2() {
		DirectChannel channel = new DirectChannel();
		return channel;
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			Message<String> message = MessageBuilder.withPayload("Hello World").build();

			messageChannel.send(message);
			reverseMessageChannel.send(message);

		};
	}

}