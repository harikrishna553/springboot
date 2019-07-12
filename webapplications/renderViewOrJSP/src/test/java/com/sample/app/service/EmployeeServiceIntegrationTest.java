package com.sample.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sample.app.App;
import com.sample.app.model.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class EmployeeServiceIntegrationTest {

	@Autowired
	private EmployeeService employeeService;

	@Test
	public void testCreateEmployee() {
		Employee emp = new Employee();
		emp.setFirstName("Ram");
		emp.setLastName("Gurram");

		Employee cratedEmp = employeeService.createEmployee(emp);

		assertEquals(cratedEmp.getFirstName(), "Ram");
		assertEquals(cratedEmp.getLastName(), "Gurram");

		Optional<Employee> empOptional = employeeService.getEmployee(cratedEmp.getId());

		if (!empOptional.isPresent()) {
			assertFalse("No employee exist with id : " + cratedEmp.getId(), true);
		}

		Employee persistedEmp = empOptional.get();

		assertEquals(persistedEmp.getFirstName(), "Ram");
		assertEquals(persistedEmp.getLastName(), "Gurram");
	}
}
