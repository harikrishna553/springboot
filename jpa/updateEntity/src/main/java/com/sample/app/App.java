package com.sample.app;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.model.Employee;
import com.sample.app.repository.EmployeeRepository;

@SpringBootApplication
public class App {

	private static void printEmployee(Employee emp) {
		System.out.println("------------------------------");
		System.out.println("Id : " + emp.getId());
		System.out.println("firstName : " + emp.getFirstName());
		System.out.println("lastName : " + emp.getLastName());
		System.out.println("------------------------------");
	}

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(EmployeeRepository employeeRepository) {
		return (args) -> {
			Employee emp1 = Employee.builder().firstName("Ram").lastName("Gurram").build();

			Employee persistedEntity = employeeRepository.save(emp1);

			printEmployee(persistedEntity);

			Optional<Employee> empOpt = employeeRepository.findById(persistedEntity.getId());

			if (!empOpt.isPresent()) {
				System.out.println("Employee not exist with given id");
				return;
			}

			Employee tempEmp = empOpt.get();
			tempEmp.setFirstName("Venkata Ramarao");
			tempEmp.setLastName("Bhadri");

			persistedEntity = employeeRepository.save(tempEmp);
			printEmployee(persistedEntity);

		};
	}

}
