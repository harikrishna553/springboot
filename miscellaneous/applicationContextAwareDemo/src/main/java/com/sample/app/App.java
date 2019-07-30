package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.model.Employee;
import com.sample.app.providers.ApplicationContextProvider;

@SpringBootApplication
public class App {

	@Autowired
	private ApplicationContextProvider contextProvider;

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			Employee emp1 = contextProvider.getApplicationContext().getBean("krishna", Employee.class);

			Employee emp2 = contextProvider.getApplicationContext().getBean("ram", Employee.class);

			emp1.print();
			emp2.print();

		};
	}

}