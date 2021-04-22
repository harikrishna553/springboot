package com.sample.app.controller;

import java.util.List;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.dto.EmployeeRequestDto;
import com.sample.app.entity.Employee;
import com.sample.app.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Employee Controller", value = "This section contains all Employee Speicifc Operations")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@Autowired
	private AuditReader auditReader;

	@ApiOperation(value = "Create new Employee", notes = "Create new Employee")
	@PostMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeRequestDto emp) {

		Employee persistedEmp = empService.save(emp);

		return new ResponseEntity<>(persistedEmp, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get Employee", notes = "Get employee by id")
	@GetMapping(value = "/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployees(@PathVariable final Integer id) {

		Employee persistedEmp = empService.byId(id);

		return new ResponseEntity<>(persistedEmp, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get Employee", notes = "Get employee by id")
	@PutMapping(value = "/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@PathVariable final Integer id,
			@RequestBody EmployeeRequestDto empDto) {

		Employee persistedEmp = empService.update(id, empDto);

		return new ResponseEntity<>(persistedEmp, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get Employee", notes = "Get employee by id")
	@GetMapping(value = "/employees/revisions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List> getEmployeeRevisions(@PathVariable final Integer id) {
		
		List contentAndRevisionsForGivenId = auditReader.createQuery().forRevisionsOfEntity(Employee.class, false, true)
				.add(AuditEntity.id().eq(id)).addOrder(AuditEntity.revisionNumber().asc()).getResultList();

		return new ResponseEntity<>(contentAndRevisionsForGivenId, HttpStatus.OK);
	}

}
