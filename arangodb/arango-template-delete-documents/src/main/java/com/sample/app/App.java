package com.sample.app;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arangodb.entity.DocumentDeleteEntity;
import com.arangodb.entity.DocumentEntity;
import com.arangodb.entity.MultiDocumentEntity;
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

			Employee emp1 = new Employee(1, "Chamu", "M");
			Employee emp2 = new Employee(2, "Krishna", "Gurram");

			DocumentEntity docEntity1 = arangoTemplate.insert(emp1);
			DocumentEntity docEntity2 = arangoTemplate.insert(emp2);

			printAllEmps();

			String docId1 = docEntity1.getId();
			String docId2 = docEntity2.getId();

			DocumentDeleteOptions deleteOptions = new DocumentDeleteOptions();
			deleteOptions.returnOld(true);

			MultiDocumentEntity<DocumentDeleteEntity> docDeleteEntity = (MultiDocumentEntity<DocumentDeleteEntity>) arangoTemplate
					.delete(Arrays.asList(docId1, docId2), Employee.class, deleteOptions);

			System.out.println("\nDeleted documents : ");
			Collection<DocumentDeleteEntity> docDeletedEntities = docDeleteEntity.getDocuments();
			for (DocumentDeleteEntity docDeletedEntity : docDeletedEntities) {
				System.out.println(docDeletedEntity.getOld());
			}

			printAllEmps();

		};
	}
}
