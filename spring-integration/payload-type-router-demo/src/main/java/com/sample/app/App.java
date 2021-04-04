package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import com.sample.app.gateway.CustomGateway;

@SpringBootApplication
@Configuration
public class App {
	@ServiceActivator(inputChannel = "payloadTypeRoutingChannel")
	@Bean
	public PayloadTypeRouter router() {
		PayloadTypeRouter router = new PayloadTypeRouter();
		router.setChannelMapping(String.class.getName(), "stringChannel");
		router.setChannelMapping(Integer.class.getName(), "integerChannel");
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
				Message<?> message = MessageBuilder.withPayload(i).build();
				customGateway.print(message);
			}

			for (int i = 0; i < 5; i++) {
				Message<?> message = MessageBuilder.withPayload("Secret message : " + i).build();
				customGateway.print(message);
			}

		};
	}

}