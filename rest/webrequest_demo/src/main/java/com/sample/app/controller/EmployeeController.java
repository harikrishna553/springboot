package com.sample.app.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.sample.app.entity.Employee;
import com.sample.app.service.EmployeeService;

@RestController
@RequestMapping(value = "/v1/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Serializable> getUserInfo(WebRequest webRequest,
			@RequestParam(name = "empId", required = true) Integer empId) {
		
		empService.printWebRequestInfo(webRequest, "From Controller layer");
		
		Employee emp = empService.getEmployee(empId);
		
		return new ResponseEntity<Serializable>(emp, HttpStatus.OK);
	}
	

}