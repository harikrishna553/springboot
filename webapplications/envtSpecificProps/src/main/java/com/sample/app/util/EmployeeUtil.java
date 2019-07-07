package com.sample.app.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.sample.app.model.Employee;

public class EmployeeUtil {

	private static int idCounter = 1;

	private static final Map<Integer, Employee> employeesRepo = new HashMap<>();

	private static Employee buildEmployee(String firstName, String lastName) {
		Employee emp = new Employee();
		emp.setId(idCounter);
		emp.setFirstName(firstName);
		emp.setLastName(lastName);

		idCounter++;

		return emp;
	}

	static {
		Employee emp1 = buildEmployee("Deepak", "Moud");
		Employee emp2 = buildEmployee("Srinivasa Rao", "Gumma");
		Employee emp3 = buildEmployee("Purna Chandra", "Rao");
		Employee emp4 = buildEmployee("Madhavi Latha", "Gumma");
		Employee emp5 = buildEmployee("Raghava", "Reddy");

		employeesRepo.put(emp1.getId(), emp1);
		employeesRepo.put(emp2.getId(), emp2);
		employeesRepo.put(emp3.getId(), emp3);
		employeesRepo.put(emp4.getId(), emp4);
		employeesRepo.put(emp5.getId(), emp5);

	}

	public static Collection<Employee> all() {
		return employeesRepo.values();
	}

	public static Employee byId(int id) {
		return employeesRepo.get(id);
	}

	public static Employee create(String firstName, String lastName) {
		Employee emp = buildEmployee(firstName, lastName);
		employeesRepo.put(emp.getId(), emp);
		return emp;
	}

	public static Employee create(Employee emp) {
		return create(emp.getFirstName(), emp.getLastName());
	}

	public static Employee delete(int id) {
		return employeesRepo.remove(id);
	}

	public static Employee updateById(int id, Employee emp) {
		emp.setId(id);
		return employeesRepo.put(id, emp);
	}
}
