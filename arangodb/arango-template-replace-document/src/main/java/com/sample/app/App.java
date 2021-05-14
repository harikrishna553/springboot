package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arangodb.entity.DocumentCreateEntity;
import com.arangodb.entity.DocumentUpdateEntity;
import com.arangodb.model.DocumentCreateOptions;
import com.arangodb.model.DocumentReplaceOptions;
import com.arangodb.springframework.core.ArangoOperations;
import com.sample.app.entity.Employee;

@SpringBootApplication
public class App {

	@Autowired
	private ArangoOperations arangoTemplate;

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	private static void print(DocumentCreateEntity documentCreateEntity) {
		System.out.println("\nid : " + documentCreateEntity.getId());
		System.out.println("key : " + documentCreateEntity.getKey());
		System.out.println("revision : " + documentCreateEntity.getRev());
		System.out.println("doc : " + documentCreateEntity.getNew());
	}

	@Bean
	public CommandLineRunner demo() {

		return (args) -> {
			Employee emp1 = new Employee(1, "Shanmukha Rao", "Kummari");

			DocumentCreateOptions docCreateOptions = new DocumentCreateOptions();
			docCreateOptions.returnNew(true);

			DocumentCreateEntity documentCreateEntity1 = (DocumentCreateEntity) arangoTemplate.insert(emp1,
					docCreateOptions);

			print(documentCreateEntity1);

			Employee emp1ToReplace = new Employee();
			emp1ToReplace.setKey(documentCreateEntity1.getKey());
			emp1ToReplace.setFirstName("Shanmukha Rao Kummari");
			emp1ToReplace.setAge(35);

			DocumentReplaceOptions docReplaceOptions = new DocumentReplaceOptions();
			docReplaceOptions.returnNew(true);
			docReplaceOptions.returnOld(true);

			DocumentUpdateEntity updatedDoc = (DocumentUpdateEntity) arangoTemplate.replace(documentCreateEntity1.getId(),
					emp1ToReplace, docReplaceOptions);

			System.out.println("\nUpdated document");
			System.out.println("\nid : " + updatedDoc.getId());
			System.out.println("key : " + updatedDoc.getKey());
			System.out.println("revision : " + updatedDoc.getRev());
			System.out.println("newDoc : " + updatedDoc.getNew());
			System.out.println("oldDoc : " + updatedDoc.getOld());

		};
	}
}
