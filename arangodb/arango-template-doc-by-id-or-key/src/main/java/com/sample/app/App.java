package com.sample.app;

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

			DocumentCreateEntity docCreateEntity = (DocumentCreateEntity) arangoTemplate.insert(emp1);

			String docId = docCreateEntity.getId();
			String docKey = docCreateEntity.getKey();

			DocumentReadOptions docReadOptions = new DocumentReadOptions();
			docReadOptions.allowDirtyRead(true);

			Optional<Employee> empOptById = arangoTemplate.find(docId, Employee.class, docReadOptions);
			Optional<Employee> empOptByKey = arangoTemplate.find(docKey, Employee.class, docReadOptions);

			System.out.println("by id : " + empOptById.get());
			System.out.println("by key : " + empOptByKey.get());

		};
	}
}
