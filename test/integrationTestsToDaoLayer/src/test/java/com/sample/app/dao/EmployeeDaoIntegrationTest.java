package com.sample.app.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sample.app.dao.impl.EmployeeDaoImpl;
import com.sample.app.model.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class EmployeeDaoIntegrationTest {

	@Autowired
	private EmployeeDaoImpl employeeDao;

	@Test
	public void newEmployee() {
		Employee emp1 = new Employee();
		emp1.setFirstName("Anil");
		emp1.setLastName("Kumar");

		Employee persistedEmployee = employeeDao.save(emp1);

		assertNotNull(persistedEmployee);
		assertNotNull(persistedEmployee.getId());
		assertEquals(emp1.getFirstName(), persistedEmployee.getFirstName());
		assertEquals(emp1.getLastName(), persistedEmployee.getLastName());

	}

}
