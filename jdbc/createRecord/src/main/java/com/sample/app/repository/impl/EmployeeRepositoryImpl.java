package com.sample.app.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Employee save(Employee emp) {
		int affectedRows = jdbcTemplate.update("INSERT INTO employees (id, first_name, last_name) VALUES (?, ?, ?)", emp.getId(),
				emp.getFirstName(), emp.getLastName());

		System.out.println("Affected Rows : " + affectedRows);
		
		Employee emp1 = new Employee();
		jdbcTemplate.query("SELECT * FROM employees WHERE id = " + emp.getId(), rs -> {
			emp1.setId(rs.getInt("id"));
			emp1.setFirstName(rs.getString("first_name"));
			emp1.setLastName(rs.getString("last_name"));
		});
		return emp1;
	}

}
