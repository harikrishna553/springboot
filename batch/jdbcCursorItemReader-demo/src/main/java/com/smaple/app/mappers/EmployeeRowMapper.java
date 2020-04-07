package com.smaple.app.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sample.app.entity.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

		Employee emp = new Employee();

		emp.setId(rs.getInt("id"));
		emp.setFirstName(rs.getString("first_name"));
		emp.setLastName(rs.getString("last_name"));

		return emp;
	}

}
