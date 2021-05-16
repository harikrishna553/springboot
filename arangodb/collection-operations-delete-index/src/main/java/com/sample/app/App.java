package com.sample.app;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arangodb.entity.IndexEntity;
import com.arangodb.model.GeoIndexOptions;
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

	private static void printIndexes(CollectionOperations collectionOperations) {
		Collection<IndexEntity> indexes = collectionOperations.getIndexes();

		System.out.println("\nTotal indexes : " + indexes.size());

		for (IndexEntity indexEntity : indexes) {
			System.out.println("\nindex details");
			System.out.println("\tname : " + indexEntity.getName());
			System.out.println("\tIndex created on fields : " + indexEntity.getFields());
		}

	}

	@Bean
	public CommandLineRunner demo() {

		return (args) -> {
			Employee emp1 = new Employee(1, "Krishna", 29.9792, 31.1342);

			arangoTemplate.insert(emp1);

			Iterable<Employee> empsIterable = arangoTemplate.findAll(Employee.class);

			System.out.println("Documents in employees collection");
			for (Employee emp : empsIterable) {
				System.out.println(emp);
			}

			CollectionOperations empsCollection = arangoTemplate.collection(Employee.class);

			GeoIndexOptions gersistentIndexOptions = new GeoIndexOptions();
			gersistentIndexOptions.name("locationIndex");

			IndexEntity indexEntity = empsCollection
					.ensureGeoIndex(Arrays.asList("locationLongitude", "locationLatitude"), gersistentIndexOptions);

			printIndexes(empsCollection);

			System.out.println("\nDropping the index 'locationIndex'\n");
			empsCollection.dropIndex(indexEntity.getId());

			printIndexes(empsCollection);

			System.out.println("\nDropping the collection");

			empsCollection.drop();

		};
	}
}