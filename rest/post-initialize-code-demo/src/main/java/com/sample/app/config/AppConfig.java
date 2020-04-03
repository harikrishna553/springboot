package com.sample.app.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class AppConfig {

	@PostConstruct
	public void postConstructCode() {
		System.out.println("Spring Boot Application started succesfully..Executing code using PostConstruct!!!!!");
	}

	@EventListener(ApplicationReadyEvent.class)
	public void appReadyEventCode() {
		System.out.println(
				"Spring Boot Application started succesfully..Executing code using ApplicationReadyEvent!!!!!");
	}

	@Bean
	public CommandLineRunner commandLinerRunner() {
		return (args) -> {
			System.out.println(
					"Spring Boot Application started succesfully..Executing code using CommandLineRunner!!!!!");
		};
	}

	@Bean
	public SmartInitializingSingleton smartInitializingSingleton() {
		return () -> {
			System.out.println(
					"Spring Boot Application started succesfully..Executing code using SmartInitializingSingleton!!!!!");
		};

	}

}



