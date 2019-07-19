package com.sample.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App1 {

	public static void main(String[] args) {
		System.out.println("I am in App1 class");
		SpringApplication.run(App1.class, args);
	}

}
