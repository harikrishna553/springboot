package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import com.sample.app.endpoints.CustomGateway;

@SpringBootApplication
@Configuration
public class App {

	@Autowired
	@Qualifier("pubSubChannel")
	private PublishSubscribeChannel pubSubChannel;

	@Autowired
	private CustomGateway customGateway;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean(name = "pubSubChannel")
	public PublishSubscribeChannel channel1() {
		PublishSubscribeChannel channel = new PublishSubscribeChannel();
		return channel;
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			for (int i = 0; i < 10; i++) {
				Message<String> message = MessageBuilder.withPayload("Msg " + i).build();
				Message<String> result = customGateway.print(message);
				System.out.println(result.getPayload());
			}

		};
	}

}
