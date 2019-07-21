package com.sample.app.repository.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Employee save(Employee emp) {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

		List<String> columns = Arrays.asList("id", "first_name", "last_name");

		simpleJdbcInsert.setTableName("employees");
		simpleJdbcInsert.setColumnNames(columns);

		Map<String, Object> data = new HashMap<>();
		data.put("id", emp.getId());
		data.put("first_name", emp.getFirstName());
		data.put("last_name", emp.getLastName());

		int affectedRows = simpleJdbcInsert.execute(data);

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
