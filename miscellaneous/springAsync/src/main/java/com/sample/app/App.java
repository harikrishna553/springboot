package com.sample.app;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.util.TestUtil;

@SpringBootApplication
public class App {

	@Autowired
	private TestUtil testUtil;

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			CompletableFuture<String> helloFuture = testUtil.sayHello();
			CompletableFuture<String> welcomeFuture = testUtil.welcomeUser();

			CompletableFuture.allOf(helloFuture, welcomeFuture).join();

			System.out.println("**************************");
			System.out.println(helloFuture.get());
			System.out.println(welcomeFuture.get());
			System.out.println("**************************");
		};
	}
}
