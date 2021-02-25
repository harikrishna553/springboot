package com.sample.app.controller;

import java.io.Serializable;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.entity.Employee;
import com.sample.app.exception.BaseException;
import com.sample.app.service.ETagService;
import com.sample.app.service.EmployeeService;

@RestController
@RequestMapping(value = "/v1/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Serializable> getUserInfo(@RequestParam(name = "empId", required = true) Integer empId) throws BaseException {

		Employee emp = empService.getEmployee(empId);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLastModified(emp.getLastUpdateTs().toInstant());

		responseHeaders.setETag(ETagService.getETag(emp));

		return ResponseEntity.ok().headers(responseHeaders).body(emp);
	}

	@PutMapping
	public ResponseEntity<Serializable> updateEmployee(@RequestBody Employee emp) throws ParseException, BaseException {
		Employee emp1 = empService.updateEmployee(emp);
		return ResponseEntity.ok().body(emp1);

	}

	@PutMapping("/via-etag")
	public ResponseEntity<Serializable> updateEmployeeViaEtag(@RequestBody Employee emp)
			throws ParseException, BaseException {
		Employee emp1 = empService.updateEmployeeViaEtag(emp);
		return ResponseEntity.ok().body(emp1);

	}
}
