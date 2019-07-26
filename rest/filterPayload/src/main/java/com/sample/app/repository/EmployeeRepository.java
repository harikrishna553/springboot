package com.sample.app.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sample.app.model.Employee;

@Repository
public class EmployeeRepository {

	private static final List<Employee> emps = new ArrayList<>();

	static {
		Employee emp1 = new Employee(1, "Sunil", "Dayanand", "India");
		Employee emp2 = new Employee(2, "Keerthi", "Shetty", "India");
		Employee emp3 = new Employee(3, "Ram", "Anand", "United States Of America");

		emps.add(emp1);
		emps.add(emp2);
		emps.add(emp3);

	}

	public List<Employee> all() {
		return emps;
	}

	public Map<String, Object> filterData(String filter, int id) {

		if (filter == null)
			return Collections.EMPTY_MAP;

		Employee emp = byId(id);
		if (emp == null)
			return Collections.EMPTY_MAP;

		Map<String, Object> map = new HashMap<>();

		String[] fields = filter.split(",");

		for (String field : fields) {
			if (field.equalsIgnoreCase("id")) {
				map.put("id", emp.getId());
			}

			if (field.equalsIgnoreCase("firstName")) {
				map.put("firstName", emp.getFirstName());
			}

			if (field.equalsIgnoreCase("lastName")) {
				map.put("lastName", emp.getLastName());
			}

			if (field.equalsIgnoreCase("country")) {
				map.put("country", emp.getCountry());
			}

		}

		return map;

	}

	private static Employee byId(int id) {
		for (Employee emp : emps) {
			if (id == emp.getId()) {
				return emp;
			}
		}

		return null;
	}
}