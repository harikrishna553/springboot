package com.sample.app.service;

import java.text.ParseException;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import com.sample.app.entity.Employee;
import com.sample.app.exception.BaseException;
import com.sample.app.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private WebRequest webRequest;

	public Employee getEmployee(Integer id) {
		return empRepo.findById(id).get();
	}

	public Employee updateEmployee(Employee emp) throws ParseException, BaseException {
		Employee persisitedEmp = this.getEmployee(emp.getId());
		Instant instant = persisitedEmp.getLastUpdateTs().toInstant();

		boolean isModified = webRequest.checkNotModified(instant.toEpochMilli());
		if (isModified) {
			throw new BaseException("You are working on old resource");
		}

		return this.empRepo.save(emp);

	}

	public Employee updateEmployeeViaEtag(Employee emp) throws ParseException, BaseException {
		Employee persisitedEmp = this.getEmployee(emp.getId());
		String persistedEtagValue = ETagService.getETag(persisitedEmp);

		String eTagValue = webRequest.getHeader("ETag");

		// Somehow checkNotModified is not working with etag
		// boolean isModified = webRequest.checkNotModified(eTagValue);
		boolean isModified = persistedEtagValue.equals(eTagValue);
		if (!isModified) {
			throw new BaseException("You are working on old resource");
		}

		return this.empRepo.save(emp);

	}

}



