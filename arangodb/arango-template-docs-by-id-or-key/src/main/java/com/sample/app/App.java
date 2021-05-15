package com.sample.app;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arangodb.entity.DocumentCreateEntity;
import com.arangodb.model.DocumentReadOptions;
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
			CollectionOperations empsCollection = arangoTemplate.collection(Employee.class);
			empsCollection.drop();

			Employee emp1 = new Employee(1, "Chamu", "M");
			Employee emp2 = new Employee(2, "Krishna", "Gurram");

			DocumentCreateEntity docCreateEntity1 = (DocumentCreateEntity) arangoTemplate.insert(emp1);
			DocumentCreateEntity docCreateEntity2 = (DocumentCreateEntity) arangoTemplate.insert(emp2);

			String doc1Id = docCreateEntity1.getId();
			String doc2Key = docCreateEntity2.getKey();

			Iterable<Employee> empsIterable = arangoTemplate.find(Arrays.asList(doc1Id, doc2Key), Employee.class);

			for (Employee emp : empsIterable) {
				System.out.println(emp);
			}

		};
	}
}
