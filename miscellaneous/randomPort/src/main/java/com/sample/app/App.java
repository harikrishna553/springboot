package com.sample.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
	public static void main(String[] args) {

		//SpringApplication.run(App.class, args);
		
//		Map<String, Object> props = new HashMap<>();
//		props.put("server.port", 1234);
//
//		new SpringApplicationBuilder()
//		    .sources(App.class)                
//		    .properties(props)
//		    .run(args);
		
		System.setProperty("server.port","0");
		SpringApplication.run(App.class, args);
	}

	
}