package com.sample.app;

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
	public CommandLineRunner demo(EmployeeRepository employeeRepo) {

		return (args) -> {

			Employee emp1 = new Employee(1, "Ram", "Gurram", 31);
			Employee emp2 = new Employee(2, "Rudra", "Gurram", 32);
			Employee emp3 = new Employee(3, "Gopi", "Battu", 31);
			Employee emp4 = new Employee(4, "Joel", "Chelli", 32);
			Employee emp5 = new Employee(5, "Sailu", "Ptr", 31);
			Employee emp6 = new Employee(6, "Lahari", "Gurram", 23);

			emp1 = employeeRepo.save(emp1);
			emp2 = employeeRepo.save(emp2);
			emp3 = employeeRepo.save(emp3);
			emp4 = employeeRepo.save(emp4);
			emp5 = employeeRepo.save(emp5);
			emp6 = employeeRepo.save(emp6);

			List<Employee> empsWithLastNameGurram = employeeRepo.getWithLastName("Gurram");
			for (Employee emp : empsWithLastNameGurram) {
				System.out.println(emp);
			}

		};
	}
}