package com.sample.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;

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
			List<Employee> emps = Arrays.asList(new Employee(1, "Ram", "Gurram", 31),
					new Employee(2, "Sundar", "Deavanagari", 32), new Employee(3, "Srinuvasa rao", "Guma", 43),
					new Employee(4, "Madhavi Latha", "Gumma", 41), new Employee(5, "Gopi", "Battu", 33),
					new Employee(6, "Sailu", "PTR", 33), new Employee(7, "Krishna", "Gurram", 32));

			employeeRepo.saveAll(emps);

			Employee empWithAge32Example = new Employee();
			empWithAge32Example.setAge(32);

			Iterable<Employee> empsWithAge32 = employeeRepo.findAll(Example.of(empWithAge32Example));

			System.out.println("Employees with age 32");

			for (Employee emp : empsWithAge32) {
				System.out.println(emp);
			}

		};
	}
}