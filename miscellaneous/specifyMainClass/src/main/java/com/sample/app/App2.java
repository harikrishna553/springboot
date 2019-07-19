package com.sample.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App2 {

	public static void main(String[] args) {
		System.out.println("I am in App2 class");
		SpringApplication.run(App1.class, args);
	}

}
