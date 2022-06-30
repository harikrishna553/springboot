package com.sample.app;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.model.Employee;
import com.sample.app.service.EmployeeService;

@SpringBootApplication
public class App {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	@Autowired
	private EmployeeService empService;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	public void getEmployeeDetails() {

		System.out.println("\n");
		LOGGER.info("\nGetting employee details with id 1");
		Employee emp = empService.getEmployeeById(1);
		LOGGER.info("emp : {}", emp);

		System.out.println("\n");
		LOGGER.info("Getting employee details with name 'Krishna'");
		emp = empService.getEmployeeByFirstName("Krishna");
		LOGGER.info("emp : {}", emp);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			getEmployeeDetails();

			LOGGER.info("Sleeping for 4 seconds");
			TimeUnit.SECONDS.sleep(4);
			getEmployeeDetails();

			LOGGER.info("Sleeping for 4 seconds");
			TimeUnit.SECONDS.sleep(4);
			getEmployeeDetails();

			LOGGER.info("Sleeping for 4 seconds");
			TimeUnit.SECONDS.sleep(4);
			getEmployeeDetails();

		};
	}
}