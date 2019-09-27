package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

@SpringBootApplication
public class App {
	@Autowired
	private EmployeeRepository empRepo;

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			Employee emp1 = new Employee();
			emp1.setFirstName("Ram");
			emp1.setLastName("Ponnam");

			empRepo.save(emp1);

			empRepo.findAll().forEach(System.out::println);
		};
	}

}
