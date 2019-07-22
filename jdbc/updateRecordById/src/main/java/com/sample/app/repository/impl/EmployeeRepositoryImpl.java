package com.sample.app.repository.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.setGeneratedKeyName("id");

		List<String> columns = Arrays.asList("first_name", "last_name");

		simpleJdbcInsert.setTableName("employees");
		simpleJdbcInsert.setColumnNames(columns);

		Map<String, Object> data = new HashMap<>();
		data.put("first_name", emp.getFirstName());
		data.put("last_name", emp.getLastName());

		int id = (Integer) simpleJdbcInsert.executeAndReturnKey(data);

		Employee emp1 = new Employee();
		jdbcTemplate.query("SELECT * FROM employees WHERE id = " + id, rs -> {
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

	@Override
	public Employee get(int id) {
		try {

			return jdbcTemplate.queryForObject("SELECT * FROM employees WHERE id=?", EMPLOYEE_ROW_MAPPER, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public Employee update(Employee emp) {
		jdbcTemplate.update("UPDATE employees SET first_name=?, last_name=? WHERE id=?", emp.getFirstName(),
				emp.getLastName(), emp.getId());
		return emp;
	}

}
