package com.sample.app.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeCustomRepository;

@Repository
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> emps(List<Integer> empIds) {

		Query query = entityManager.createNativeQuery("SELECT * FROM employees e WHERE e.id IN " + inQuery(empIds),
				Employee.class);

		return query.getResultList();
	}

	private static final String inQuery(List<Integer> empIds) {
		StringBuilder builder = new StringBuilder();

		builder.append("(");

		for (int id : empIds) {
			builder.append(id).append(",");
		}

		String str = builder.toString();

		String result = str.substring(0, str.length() - 1) + ")";
		return result;
	}

}
