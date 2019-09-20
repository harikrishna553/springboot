package com.sample.app;

import java.util.Arrays;
import java.util.List;

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

			Employee emp1 = new Employee(1, "Harini", "Maj");
			Employee emp2 = new Employee(2, "Surya", "Konagandla");
			Employee emp3 = new Employee(3, "Ram", "Gunju");
			Employee emp4 = new Employee(4, "Rahim", "Khan");
			Employee emp5 = new Employee(5, "Joel", "Chelli");

			empRepo.saveAll(Arrays.asList(emp1, emp2, emp3, emp4, emp5));

			List<Object[]> emps = empRepo.findFirstAndLastNames(Arrays.asList(2, 3, 5));

			if (emps == null || emps.isEmpty())
				return;

			for (Object[] obj : emps) {
				System.out.println(obj[0] + "," + obj[1]);
			}
		};

	}

}
