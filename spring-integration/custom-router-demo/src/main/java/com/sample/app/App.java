package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import com.sample.app.gateway.CustomGateway;
import com.sample.app.router.CustomRouter;

@SpringBootApplication
@Configuration
public class App {
	@Router(inputChannel = "customRouterChannel")
	@Bean
	public CustomRouter router() {
		CustomRouter customRouter = new CustomRouter();
		return customRouter;
	}

	@Bean
	public MessageChannel secretChannel() {
		DirectChannel channel = new DirectChannel();
		return channel;
	}

	@Bean
	public MessageChannel publicChannel() {
		DirectChannel channel = new DirectChannel();
		return channel;
	}

	@Bean
	public MessageChannel defaultOutputChannel() {
		DirectChannel channel = new DirectChannel();
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
