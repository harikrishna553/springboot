package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arangodb.entity.DocumentCreateEntity;
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
			
			System.out.println("id : " + docId);
			System.out.println("key : " + docKey);
			
			System.out.println("\nIs doc exists with given id (" + docId + ") ? " + arangoTemplate.exists(docId, Employee.class));
			System.out.println("Is doc exists with given key (" + docKey + ") ? " + arangoTemplate.exists(docKey, Employee.class));
			

		};
	}
}
