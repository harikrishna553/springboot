package com.sample.app;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arangodb.entity.DocumentDeleteEntity;
import com.arangodb.entity.DocumentEntity;
import com.arangodb.model.DocumentDeleteOptions;
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

	private void printAllEmps() {
		Iterable<Employee> empsIterable = arangoTemplate.findAll(Employee.class);

		Iterator<Employee> empsIterator = empsIterable.iterator();

		if (!empsIterator.hasNext()) {
			System.out.println("\nNo documents present in the collection");
		} else {
			for (Employee emp : empsIterable) {
				System.out.println(emp);
			}
		}

	}

	@Bean
	public CommandLineRunner demo() {

		return (args) -> {
			CollectionOperations empsCollection = arangoTemplate.collection(Employee.class);
			empsCollection.drop();

			Employee emp1 = new Employee(1, "Shanmukha Rao", "Kummari");

			DocumentEntity docEntity = arangoTemplate.insert(emp1);

			printAllEmps();

			String docId = docEntity.getId();

			DocumentDeleteOptions deleteOptions = new DocumentDeleteOptions();
			deleteOptions.returnOld(true);

			DocumentDeleteEntity docDeleteEntity = (DocumentDeleteEntity) arangoTemplate.delete(docId, Employee.class,
					deleteOptions);

			System.out.println("\nDeleted document : " + docDeleteEntity.getOld());

			printAllEmps();

		};
	}
}
