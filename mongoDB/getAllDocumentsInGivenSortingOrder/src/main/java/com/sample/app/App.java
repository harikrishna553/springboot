package com.sample.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

@SpringBootApplication
public class App {
	@Value("${spring.data.mongodb.database}")
	private String database;

	@Value("${spring.data.mongodb.host}")
	private String host;

	@Value("${spring.data.mongodb.port}")
	private int port;

	@Autowired
	private EmployeeRepository empRepository;

	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}

	public void dropPreviousData() {
		MongoClient mongoClient = new MongoClient(host, port);

		MongoOperations mongoOps = new MongoTemplate(mongoClient, database);

		mongoOps.dropCollection("employees");
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			dropPreviousData();

			Employee emp1 = new Employee("Phalgun", "Garimella");
			Employee emp2 = new Employee("Sankalp", "Dubey");
			Employee emp3 = new Employee("Arpan", "Debroy");

			empRepository.saveAll(Arrays.asList(emp1, emp2, emp3));

			List<Employee> emps = empRepository.findAll(new Sort(Direction.ASC, "firstName"));

			System.out.println("Sort employees by first names");
			for (Employee emp : emps) {
				System.out.println(emp);
			}

			emps = empRepository.findAll(new Sort(Direction.ASC, "lastName"));

			System.out.println("\nSort employees by last names");
			for (Employee emp : emps) {
				System.out.println(emp);
			}

		};
	}
}
