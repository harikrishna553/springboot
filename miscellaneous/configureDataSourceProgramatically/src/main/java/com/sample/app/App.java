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

	public static void main(String[] args) {
		SpringApplication.run(App.class);
	}

	@Bean
	public CommandLineRunner demo(EmployeeRepository employeeRepository) {
		return (args) -> {
			Employee emp1 = new Employee();
			emp1.setFirstName("Hari Krishna");
			emp1.setLastName("Ponnam");
			
			Employee emp2 = new Employee();
			emp2.setFirstName("Krishna");
			emp2.setLastName("Gurram");

			List<Employee> emps = Arrays.asList(emp1, emp2);
			
			employeeRepository.saveAll(emps);
			
			for(Employee emp: emps) {
				System.out.println(emp);
			}
			
		};
	}

}
