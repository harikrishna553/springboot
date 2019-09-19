package com.smaple.app.jpa.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.sample.app.model.Employee;

public class EmployeeSpecifications {

	public static Specification<Employee> getByFirstAndLastName(String firstName, String lastName) {
		return new Specification<Employee>() {

			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				Path firstNameExpression = root.get("firstName");
				Path lastNameExpression = root.get("lastName");

				predicates.add(criteriaBuilder.equal(firstNameExpression, firstName));
				predicates.add(criteriaBuilder.equal(lastNameExpression, lastName));

				if (predicates.isEmpty()) {
					return criteriaBuilder.conjunction();
				}

				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

			}

		};
	}

}


