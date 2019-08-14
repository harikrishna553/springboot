package com.sample.app.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sample.app.entity.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
	List<Employee> findByFirstName(String firstName);
	
	List<Employee> findByLastName(String lastName);
	
	List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
	
	@Query("{'firstName' : ?0}")
	Stream<Employee> findAllByCustomQueryAndStream(String firstName);
}
