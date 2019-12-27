package com.sample.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.sample.app.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}