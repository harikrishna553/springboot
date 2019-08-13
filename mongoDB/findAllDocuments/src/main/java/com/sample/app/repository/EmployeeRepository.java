package com.sample.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sample.app.entity.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
