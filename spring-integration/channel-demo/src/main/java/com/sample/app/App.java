package com.sample.app;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;

@SpringBootApplication
@Configuration
public class App {

	@Autowired
	private DirectChannel messageChannel;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public DirectChannel channel() {
		DirectChannel channel = new DirectChannel();
		return channel;
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			messageChannel.subscribe(new MessageHandler() {

				@Override
				public void handleMessage(Message<?> message) throws MessagingException {
					String payload = (String) message.getPayload();
					MessageHeaders messageHeaders = message.getHeaders();

					System.out.println("Headers");
					Set<Map.Entry<String, Object>> messageHeadersSet = messageHeaders.entrySet();

					for (Map.Entry<String, Object> headerEntry : messageHeadersSet) {
						System.out.println(headerEntry.getKey() + " -> " + headerEntry.getValue());
					}

					System.out.println("\nPayload");
					System.out.println(payload);
				}
			});
			
			

			String json = "{\"name\" : \"Krishna\"}";

			Message<String> message = MessageBuilder.withPayload(json).setHeader("my-content-type", "application/json")
					.setHeader("my-origin", "localhost").build();

			messageChannel.send(message);

		};
	}

}

