package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.config.AppConfig;

@SpringBootApplication
public class App {
	
	@Autowired
	private AppConfig appConfig;
	
	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			
			System.out.println("Application Name : " + appConfig.getAppName());
			System.out.println("Application Version : " + appConfig.getCurrentVersion());
			
			System.out.println("Supported Versions");
			appConfig.getSupportedVersions().stream().forEach(System.out::println);
		

		};
	}

}