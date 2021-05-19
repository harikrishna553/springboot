package com.sample.app;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class App {
	private static final String TOPIC_NAME = "topic2";

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public NewTopic topic() {
		return TopicBuilder.name(TOPIC_NAME).partitions(10).replicas(1).build();
	}

	@KafkaListener(id = "myId1", topics = TOPIC_NAME)
	public void listener1(String message) {
		System.out.println("listener1 received : " + message);
	}

	@KafkaListener(id = "myId2", topics = TOPIC_NAME)
	public void listener2(String message) {
		System.out.println("listener2 received : " + message);
	}

	@Bean
	public ApplicationRunner runner(KafkaTemplate<String, String> template) {
		return args -> {
			template.send(TOPIC_NAME, "Hello World");
		};
	}

}