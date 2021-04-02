package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;

import com.sample.app.endpoints.ProducerEndpoint;

@SpringBootApplication
@Configuration
public class App {

	@Autowired
	@Qualifier("inputChannel")
	private DirectChannel inputChannel;

	@Autowired
	@Qualifier("ackChannel")
	private DirectChannel ackChannel;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean(name = "inputChannel")
	public DirectChannel channel1() {
		DirectChannel channel = new DirectChannel();
		return channel;
	}

	@Bean(name = "ackChannel")
	public DirectChannel channel2() {
		DirectChannel channel = new DirectChannel();
		return channel;
	}

	@Bean
	public CommandLineRunner demo(@Autowired ProducerEndpoint producerEndpoint) {
		return (args) -> {
			producerEndpoint.produceMessage("Hello World");

		};
	}

}