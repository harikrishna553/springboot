package com.sample.app.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, rowNum) -> {
		Employee emp1 = new Employee();
		emp1.setId(rs.getInt("id"));
		emp1.setFirstName(rs.getString("first_name"));
		emp1.setLastName(rs.getString("last_name"));

		return emp1;
	};

	@Override
	public Employee save(Employee emp) {
		jdbcTemplate.update("INSERT INTO employees (id, first_name, last_name) VALUES (?, ?, ?)", emp.getId(),
				emp.getFirstName(), emp.getLastName());

		Employee emp1 = new Employee();
		jdbcTemplate.query("SELECT * FROM employees WHERE id = " + emp.getId(), rs -> {
			emp1.setId(rs.getInt("id"));
			emp1.setFirstName(rs.getString("first_name"));
			emp1.setLastName(rs.getString("last_name"));
		});
		return emp1;
	}

	@Override
	public List<Employee> all() {
		return jdbcTemplate.query("SELECT * FROM employees", EMPLOYEE_ROW_MAPPER);
	}

}


