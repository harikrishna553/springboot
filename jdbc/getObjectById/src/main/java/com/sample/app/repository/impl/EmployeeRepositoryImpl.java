package com.sample.app.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
		KeyHolder keyHolder = new GeneratedKeyHolder();

		PreparedStatementCreator preparedStmtCreator = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO employees (first_name, last_name) VALUES (?, ?)", new String[] { "id" });
				ps.setString(1, emp.getFirstName());
				ps.setString(2, emp.getLastName());
				return ps;
			}

		};

		jdbcTemplate.update(preparedStmtCreator, keyHolder);

		int id = (Integer) keyHolder.getKey();

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

}
