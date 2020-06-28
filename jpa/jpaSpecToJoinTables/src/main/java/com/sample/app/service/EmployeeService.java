package com.sample.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.sample.app.model.Employee;
import com.sample.app.model.SearchQuery;
import com.sample.app.repository.EmployeeRepository;
import com.smaple.app.jpa.specification.SpecificationUtil;

@Component
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	public List<Employee> findAll(SearchQuery searchQuery) {

		Specification<Employee> spec = SpecificationUtil.bySearchQuery(searchQuery, Employee.class);
		PageRequest pageRequest = getPageRequest(searchQuery);

		Page<Employee> page = empRepo.findAll(spec, pageRequest);

		return page.getContent();
	}

	private PageRequest getPageRequest(SearchQuery searchQuery) {

		int pageNumber = searchQuery.getPageNumber();
		int pageSize = searchQuery.getPageSize();

		List<Sort.Order> orders = new ArrayList<>();

		List<String> ascProps = searchQuery.getSortOrder().getAscendingOrder();

		if (ascProps != null && !ascProps.isEmpty()) {
			for (String prop : ascProps) {
				orders.add(Sort.Order.asc(prop));
			}
		}

		List<String> descProps = searchQuery.getSortOrder().getDescendingOrder();

		if (descProps != null && !descProps.isEmpty()) {
			for (String prop : descProps) {
				orders.add(Sort.Order.desc(prop));
			}

		}

		Sort sort = Sort.by(orders);

		return PageRequest.of(pageNumber, pageSize, sort);

	}
	
	@Transactional
	public void save(Employee emp) {
		empRepo.save(emp);
	}

}