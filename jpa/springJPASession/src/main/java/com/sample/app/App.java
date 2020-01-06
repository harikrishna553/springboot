package com.sample.app;

import java.util.List;

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
	private EmployeeRepository employeeRepo;
	
	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			Employee emp1 = new Employee("Ram", "Gurram");
			Employee emp2 = new Employee("Sai", "Nidamanuri");
			Employee emp3 = new Employee("Siva", "Ponnam");
			Employee emp4 = new Employee("Lahari", "Gurram");

			employeeRepo.save(emp1);
			employeeRepo.save(emp2);
			employeeRepo.save(emp3);
			employeeRepo.save(emp4);

			List<Employee> emps = employeeRepo.all();

			System.out.println("-------------------------");
			System.out.println("Printing Employees");
			System.out.println("-------------------------");
			for (Employee emp : emps) {
				System.out.println(emp);
			}

		};
	}

}