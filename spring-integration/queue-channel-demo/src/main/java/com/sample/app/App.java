package com.sample.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import com.sample.app.endpoints.CustomGateway;

@SpringBootApplication
@Configuration
public class App {

	@Autowired
	@Qualifier("myQueueChannel")
	private QueueChannel queueChannel;

	@Autowired
	private CustomGateway customGateway;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean(name = "myQueueChannel")
	public QueueChannel channel1() {
		QueueChannel channel = new QueueChannel(100);
		return channel;
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			List<Future<Message<String>>> futures = new ArrayList<> ();
			
			for (int i = 0; i < 10; i++) {
				System.out.println("Sending message number " + i);
				Message<String> message = MessageBuilder.withPayload("msg " + i).build();
				Future<Message<String>> result = customGateway.print(message);
				futures.add(result);
			}
			
			System.out.println("\nAll the messages sent in asynchronous way");
			
			for(Future<Message<String>> future: futures) {
				Message<String> message = future.get();
				
				System.out.println(message.getPayload());
			}

		};
	}

}