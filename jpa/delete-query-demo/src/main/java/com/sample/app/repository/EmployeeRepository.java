package com.sample.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sample.app.entity.Employee;

@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	long removeByFirstName(String lastname);

	List<Employee> deleteByFirstName(String lastname);

	long deleteByLastName(String lastname);

	List<Employee> removeByLastName(String lastname);

}