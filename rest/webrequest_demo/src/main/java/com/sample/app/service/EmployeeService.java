package com.sample.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private WebRequest webRequest;

	public Employee getEmployee(Integer id) {
		this.printWebRequestInfo(webRequest, "From Service layer");
		return empRepo.findById(id).get();
	}

	public void printWebRequestInfo(WebRequest webRequest, String message) {
		String separator = "\n-------------------------\n";
		System.out.println("\n\n");
		String empId = webRequest.getParameter("empId");

		String three_spaces = "   ";
		String six_spaces = three_spaces + three_spaces;

		StringBuilder builder = new StringBuilder();
		builder.append(message).append(separator).append("\n");
		builder.append(three_spaces).append("getParameter output : \n").append(six_spaces).append("empId : " + empId)
				.append("\n");

		System.out.println(builder.toString());

	}

}
