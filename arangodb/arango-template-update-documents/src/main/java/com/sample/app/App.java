package com.sample.app;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arangodb.entity.DocumentCreateEntity;
import com.arangodb.entity.DocumentUpdateEntity;
import com.arangodb.entity.MultiDocumentEntity;
import com.arangodb.model.DocumentCreateOptions;
import com.arangodb.model.DocumentUpdateOptions;
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
			Employee emp2 = new Employee(1, "Sarath", "Chandra");

			DocumentCreateOptions docCreateOptions = new DocumentCreateOptions();
			docCreateOptions.returnNew(true);

			DocumentCreateEntity documentCreateEntity1 = (DocumentCreateEntity) arangoTemplate.insert(emp1,
					docCreateOptions);

			DocumentCreateEntity documentCreateEntity2 = (DocumentCreateEntity) arangoTemplate.insert(emp2,
					docCreateOptions);

			print(documentCreateEntity1);
			print(documentCreateEntity2);

			Employee emp1ToUpdate = new Employee();
			emp1ToUpdate.setKey(documentCreateEntity1.getKey());
			emp1ToUpdate.setAge(35);

			Employee emp2ToUpdate = new Employee();
			emp2ToUpdate.setKey(documentCreateEntity2.getKey());
			emp2ToUpdate.setAge(42);

			DocumentUpdateOptions docUpdateOptions = new DocumentUpdateOptions();
			docUpdateOptions.returnNew(true);

			MultiDocumentEntity<DocumentUpdateEntity> updatedDocs = (MultiDocumentEntity<DocumentUpdateEntity>) arangoTemplate
					.update(Arrays.asList(emp1ToUpdate, emp2ToUpdate), Employee.class, docUpdateOptions);

			Collection<DocumentUpdateEntity> updatedDocsCollection = updatedDocs.getDocuments();

			System.out.println("\nDocuments are updated by adding age attribute");
			for (DocumentUpdateEntity updatedDoc : updatedDocsCollection) {
				System.out.println("\nid : " + updatedDoc.getId());
				System.out.println("key : " + updatedDoc.getKey());
				System.out.println("revision : " + updatedDoc.getRev());
				System.out.println("doc : " + updatedDoc.getNew());
			}

		};
	}
}
