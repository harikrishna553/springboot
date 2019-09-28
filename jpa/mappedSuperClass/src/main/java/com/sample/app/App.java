package com.sample.app;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

@SpringBootApplication
public class App {
	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(EmployeeRepository empRepo) {
		return (args) -> {
			Employee emp1 = new Employee();
			Employee emp2 = new Employee();
			Employee emp3 = new Employee();

			emp1.setFirstName("Ram");
			emp1.setLastName("Majety");
			emp1.setCreatedBy("Ram");
			emp1.setUpdatedBy("Ram");

			emp2.setFirstName("Sowmya");
			emp2.setLastName("Konagandla");
			emp2.setCreatedBy("Ram");
			emp2.setUpdatedBy("Ram");

			emp3.setFirstName("Narendra");
			emp3.setLastName("Bora");
			emp3.setCreatedBy("Ram");
			emp3.setUpdatedBy("Ram");

			empRepo.saveAll(Arrays.asList(emp1, emp2, emp3));

			empRepo.findAll().forEach(System.out::println);

		};
	}

}
