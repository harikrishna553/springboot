package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.model.UserDetails;

@SpringBootApplication
public class App {
	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	@Qualifier("myAppDetails")
	private UserDetails appDetails;
	
	@Autowired
	@Qualifier("myHiveDetails")
	private UserDetails hiveDetails;
	
	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			System.out.println(appDetails);
			System.out.println(hiveDetails);
		};
	}
}

