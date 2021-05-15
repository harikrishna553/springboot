package com.sample.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arangodb.entity.DocumentEntity;
import com.arangodb.springframework.core.ArangoOperations;
import com.sample.app.entity.Employee;

@SpringBootApplication
public class App {

	@Autowired
	private ArangoOperations arangoTemplate;

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	private void printAllEmps() {
		Iterable<Employee> empsIterable = arangoTemplate.findAll(Employee.class);

		System.out.println("\n");
		for (Employee emp : empsIterable) {
			System.out.println(emp);
		}
	}

	@Bean
	public CommandLineRunner demo() {

		return (args) -> {
			Employee emp1 = new Employee(1, "Shanmukha Rao", "Kummari");
			Employee emp2 = new Employee(2, "Sailaja", "PTR");

			DocumentEntity docEntity = arangoTemplate.insert(emp1);

			printAllEmps();

			String docId = docEntity.getId();

			Employee replaceExistingEmp = new Employee();
			replaceExistingEmp.setArangoId(docId);
			replaceExistingEmp.setFirstName("Shanmukha Rao Kummari");
			replaceExistingEmp.setAge(33);

			System.out.println("\nAfter calling repsert");
			arangoTemplate.repsert(Arrays.asList(replaceExistingEmp, emp2), Employee.class);

			printAllEmps();

		};
	}
}
