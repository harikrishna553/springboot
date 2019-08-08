package com.sample.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.sample.app.entity.Employee;

import reactor.core.publisher.Flux;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Integer> {

	public Flux<Employee> findByFirstName(String firstName);
}
