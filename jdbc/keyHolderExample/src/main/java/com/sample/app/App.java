package com.sample.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

@SpringBootApplication
public class App {
	private static final Logger log = LoggerFactory.getLogger(App.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			log.info("Creating tables");

			jdbcTemplate.execute("DROP TABLE employees IF EXISTS");
			jdbcTemplate.execute(
					"CREATE TABLE employees (id serial, first_name VARCHAR(255), last_name VARCHAR(255), PRIMARY KEY(id)) ");

			Employee emp1 = new Employee("Ram", "Gurram");
			Employee emp2 = new Employee("Moutwika", "Maj");
			Employee emp3 = new Employee("Anusha", "R");

			employeeRepository.save(emp1);
			employeeRepository.save(emp2);
			employeeRepository.save(emp3);

			System.out.println("\nPrinting All Employees\n");

			List<Employee> emps = employeeRepository.all();
			emps.forEach(customer -> System.out.println(customer));

		};
	}

}
