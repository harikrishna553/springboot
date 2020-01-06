package com.sample.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sample.app.entity.Employee;

@Repository
@Transactional
public class EmployeeRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Session getSession() {
		Session session = entityManager.unwrap(Session.class);
		return session;
	}

	public List<Employee> all() {
		Session session = getSession();
		Query query = session.createQuery("FROM Employee");
		return query.getResultList();
	}

	public void save(Employee emp) {
		getSession().save(emp);
	}

	public List<Employee> emps(int startPosition, int maxRows) {
		Session session = getSession();
		Query query = session.createQuery("FROM Employee");
		query.setFirstResult(startPosition);
		query.setMaxResults(maxRows);

		return query.getResultList();
	}

}
