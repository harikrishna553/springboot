package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.config.YAMLConfig;

@SpringBootApplication
public class App {

	@Autowired
	private YAMLConfig yamlConfig;

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			System.out.println("Name : " + yamlConfig.getName());
			System.out.println("Environment : " + yamlConfig.getEnvironment());
			System.out.println("Servers : ");
			yamlConfig.getServers().forEach(System.out::println);
			
			System.out.println("Project Technical Name : " + yamlConfig.getProjectTechName());
			System.out.println("No Of Developers: " + yamlConfig.getNoOfDevelopers());

		};

	}
}
