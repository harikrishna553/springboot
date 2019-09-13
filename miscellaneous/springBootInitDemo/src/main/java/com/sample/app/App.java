package com.sample.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

	@Value("${appName}")
	private String appName;

	@Value("${version}")
	private String version;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}

	@RestController
	public class HelloController {

		@GetMapping("/")
		public String home() {
			return appName + " : " + version;
		}
	}

}
