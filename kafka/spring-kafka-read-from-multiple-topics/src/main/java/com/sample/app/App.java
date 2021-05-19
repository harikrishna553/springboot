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
	private static final String TOPIC_NAME_1 = "topic1";
	private static final String TOPIC_NAME_2 = "topic2";

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public NewTopic topic1() {
		return TopicBuilder.name(TOPIC_NAME_1).partitions(10).replicas(1).build();
	}

	@Bean
	public NewTopic topic2() {
		return TopicBuilder.name(TOPIC_NAME_2).partitions(10).replicas(1).build();
	}

	@KafkaListener(id = "myId1", topics = { TOPIC_NAME_1, TOPIC_NAME_2 })
	public void listener1(String message) {
		System.out.println("listener1 received : '" + message + "'");
	}

	@Bean
	public ApplicationRunner runner1(KafkaTemplate<String, String> template) {
		return args -> {
			template.send(TOPIC_NAME_1, "Hello World");
		};
	}
	
	@Bean
	public ApplicationRunner runner2(KafkaTemplate<String, String> template) {
		return args -> {
			template.send(TOPIC_NAME_2, "Hi there!!!!");
		};
	}

}