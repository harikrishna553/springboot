package com.sample.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sample.app.dao.EmployeeDao;
import com.sample.app.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Employee emp) {
		entityManager.persist(emp);
	}

	@Override
	public List<Employee> all() {
		Query query = entityManager.createQuery("select e from Employee e", Employee.class);
		return query.getResultList();
	}

}
