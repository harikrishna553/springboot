package com.sample.app.ApplicationClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationClientApplication.class, args);
	}

}
