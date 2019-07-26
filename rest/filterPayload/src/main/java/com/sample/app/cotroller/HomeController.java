package com.sample.app.cotroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.repository.EmployeeRepository;

@RestController
public class HomeController {

	@Autowired
	EmployeeRepository empRepo;

	@RequestMapping("/")
	public String home() {
		return "Welcome to spring boot REST App development";
	}

	@RequestMapping("/employees/{id}")
	public ResponseEntity<Map<String, Object>> welcomeMe(@RequestParam(name = "filter") String filter,
			@PathVariable (value = "id") int id) {
		return ResponseEntity.ok(empRepo.filterData(filter, id));
	}

}
