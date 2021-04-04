package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.router.HeaderValueRouter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import com.sample.app.gateway.CustomGateway;

@SpringBootApplication
@Configuration
public class App {
	@ServiceActivator(inputChannel = "headerValueRouterChannel")
	@Bean
	public HeaderValueRouter router() {
		HeaderValueRouter router = new HeaderValueRouter("messageType");

		router.setChannelMapping("secret", "secretChannel");
		router.setChannelMapping("public", "publicChannel");
		return router;
	}

	@Autowired
	private CustomGateway customGateway;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			for (int i = 0; i < 5; i++) {
				Message<?> message = null;
				if (i % 2 == 0) {
					message = MessageBuilder.withPayload("secret message " + i).setHeader("messageType", "secret")
							.build();
				} else {
					message = MessageBuilder.withPayload("public message " + i).setHeader("messageType", "public")
							.build();
				}

				customGateway.print(message);
			}

		};
	}

}


