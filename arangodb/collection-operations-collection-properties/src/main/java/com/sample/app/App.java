package com.sample.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arangodb.entity.CollectionPropertiesEntity;
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

	private static void printCollectionProperties(CollectionPropertiesEntity collectionPropertiesEntity) {
		System.out.println("id :  " + collectionPropertiesEntity.getId());
		System.out.println("name : " + collectionPropertiesEntity.getName());
		System.out.println("Sharding strategy : " + collectionPropertiesEntity.getShardingStrategy());
		System.out.println("Smart join attribute : " + collectionPropertiesEntity.getSmartJoinAttribute());
		System.out.println("Index buckets : " + collectionPropertiesEntity.getIndexBuckets());
		System.out.println("Journal size : " + collectionPropertiesEntity.getJournalSize());
		System.out.println("Minimum replication factor : " + collectionPropertiesEntity.getMinReplicationFactor());
		System.out.println("Number of shards " + collectionPropertiesEntity.getNumberOfShards());
	}

	@Bean
	public CommandLineRunner demo() {

		return (args) -> {
			Employee emp1 = new Employee(1, "Krishna", "Gurram", 33);
			Employee emp2 = new Employee(2, "Lahari", "G", 20);
			Employee emp3 = new Employee(3, "Thulasi", "G", 19);

			arangoTemplate.insert(Arrays.asList(emp1, emp2, emp3), Employee.class);

			Iterable<Employee> empsIterable = arangoTemplate.findAll(Employee.class);

			System.out.println("Documents in employees collection");
			for (Employee emp : empsIterable) {
				System.out.println(emp);
			}

			CollectionOperations empsCollection = arangoTemplate.collection(Employee.class);

			CollectionPropertiesEntity collectionPropertiesEntity = empsCollection.getProperties();
			printCollectionProperties(collectionPropertiesEntity);

			System.out.println("\nDropping the collection");

			empsCollection.drop();

		};
	}
}
