package com.sample.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arangodb.springframework.core.ArangoOperations;
import com.arangodb.springframework.core.CollectionOperations;
import com.sample.app.entity.Employee;

@SpringBootApplication
public class App {

	@Autowired
	private ArangoOperations arangoTemplate;

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {

		return (args) -> {
			Employee emp1 = new Employee(1, "Ram", "Gurram", 33);
			Employee emp2 = new Employee(2, "Lahari", "G", 20);
			Employee emp3 = new Employee(3, "Thulasi", "G", 19);

			arangoTemplate.insert(Arrays.asList(emp1, emp2, emp3), Employee.class);

			Iterable<Employee> empsIterable = arangoTemplate.findAll(Employee.class);

			System.out.println("Documents in employees collection");
			for (Employee emp : empsIterable) {
				System.out.println(emp);
			}

			CollectionOperations empsCollection = arangoTemplate.collection(Employee.class);

			System.out.println("\nDeleting all the documents from the collection");
			empsCollection.truncate();

			empsIterable = arangoTemplate.findAll(Employee.class);

			System.out.println("\nDocuments in employees collection");
			for (Employee emp : empsIterable) {
				System.out.println(emp);
			}

			empsCollection.drop();

		};
	}
}
