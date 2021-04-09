package com.sample.app.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sample.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	@Transactional
	@Modifying
	@Query("update Employee e set e.firstName = ?1, e.lastName = ?2 where e.id = ?3")
	int updateUserNameById(String firstname, String lastname, Integer userId);
	
	@Transactional
	@Modifying
	@Query("update Employee e set e.age = e.age+1")
	int updateEmpsAgeBy1();
}

