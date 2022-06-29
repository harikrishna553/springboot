package com.sample.app;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.model.Employee;
import com.sample.app.service.EmployeeService;

@SpringBootApplication
public class App {

	@Autowired
	private EmployeeService empService;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	public void getEmployeeDetails() {

		Employee emp = empService.getEmployeeById(1);
		System.out.println(emp);

		emp = empService.getEmployeeByFirstName("Krishna");
		System.out.println(emp);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			getEmployeeDetails();

			System.out.println("\nSleeping for 4 seconds");
			TimeUnit.SECONDS.sleep(4);
			getEmployeeDetails();

			System.out.println("\n\nSleeping for 4 seconds");
			TimeUnit.SECONDS.sleep(4);
			getEmployeeDetails();

			System.out.println("\n\nSleeping for 4 seconds");
			TimeUnit.SECONDS.sleep(4);
			getEmployeeDetails();

		};
	}
}