package com.sample.app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import com.sample.app.endpoints.CustomGateway;

@SpringBootApplication
@Configuration
public class App {

	@Autowired
	@Qualifier("myQueueChannel")
	private PriorityChannel queueChannel;

	@Autowired
	private CustomGateway customGateway;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean(name = "myQueueChannel")
	public PriorityChannel channel1() {
		PriorityChannel channel = new PriorityChannel(100, msgComparator);
		return channel;
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			List<Future<Message<Integer>>> futures = new ArrayList<>();

			for (int i = 0; i < 10; i++) {
				Message<Integer> message = MessageBuilder.withPayload(i).build();
				Future<Message<Integer>> result = customGateway.print(message);
				futures.add(result);
			}

			System.out.println("\nAll the messages sent in asynchronous way");

			for (Future<Message<Integer>> future : futures) {
				Message<Integer> message = future.get();

				System.out.println(message.getPayload());
			}

		};
	}

	private static Comparator<Message<?>> msgComparator = new Comparator<Message<?>>() {

		@Override
		public int compare(Message<?> o1, Message<?> o2) {
			Integer msg1 = (Integer) o1.getPayload();
			Integer msg2 = (Integer) o2.getPayload();

			if(isEven(msg1) && isEven(msg2)) {
				return 0;
			}else if(isEven(msg1)) {
				return -1;
			}
			
			return 1;

		}

	};
	
	private static boolean isEven(Integer num) {
		return num % 2 ==0;
	}

}

