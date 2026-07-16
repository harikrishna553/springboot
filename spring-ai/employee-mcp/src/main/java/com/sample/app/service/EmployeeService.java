package com.sample.app.service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sample.app.model.CountByGroup;
import com.sample.app.model.Employee;
import com.sample.app.model.EmployeeCount;
import com.sample.app.model.PagedResponse;
import com.sample.app.repository.EmployeeRepository;
import com.sample.app.util.PaginationUtil;

/**
 * Core business logic for all employee operations.
 *
 * <p>
 * This service is the <b>single source of truth</b> for employee data
 * operations. All MCP tools delegate here — they never access the repository
 * directly. This enforces the <b>Single Responsibility Principle</b>: tools
 * handle MCP protocol concerns; this service handles business logic.
 *
 * <p>
 * Uses Java Streams for expressive, functional data transformations throughout.
 */
@Service
public class EmployeeService {
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

	private final EmployeeRepository repository;

	/**
	 * Constructor injection (preferred over field injection for testability).
	 *
	 * @param repository the in-memory employee repository
	 */
	public EmployeeService(EmployeeRepository repository) {
		this.repository = repository;
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Tool 1 — Get All Employees (Paginated)
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Returns a paginated list of all employees, sorted by ID.
	 *
	 * @param pageNumber zero-based page index
	 * @param pageSize   number of employees per page
	 * @return paginated response with metadata
	 */
	public PagedResponse<Employee> getEmployees(int pageNumber, int pageSize) {
		log.debug("getEmployees(page={}, size={})", pageNumber, pageSize);
		List<Employee> all = repository.findAll();
		return PaginationUtil.paginate(all, pageNumber, pageSize);
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Tool 2 — Get Employees Under a Manager
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Returns all employees who directly report to the given manager.
	 *
	 * @param managerId the manager's employee ID
	 * @return list of direct reports
	 * @throws ManagerNotFoundException if no employee exists with that ID, or that
	 *                                  employee has no direct reports
	 */
	public List<Employee> getEmployeesUnderManager(Long managerId) {
		log.debug("getEmployeesUnderManager(managerId={})", managerId);

		// Validate that the manager exists
		boolean managerExists = repository.findAll().stream().anyMatch(e -> e.getId().equals(managerId));
		if (!managerExists) {
			throw new RuntimeException("Manager not found with ID: " + managerId);
		}

		List<Employee> directReports = repository.findAll().stream().filter(e -> managerId.equals(e.getManagerId()))
				.sorted(Comparator.comparing(Employee::getFullName)).collect(Collectors.toList());

		if (directReports.isEmpty()) {
			throw new RuntimeException("No employees found reporting to manager with ID: " + managerId);
		}

		log.debug("Found {} direct reports for manager id={}", directReports.size(), managerId);
		return directReports;
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Tool 3 — Get Employees by City (Paginated)
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Returns paginated employees whose office city matches (case-insensitive).
	 *
	 * @param city       the city name to filter by
	 * @param pageNumber zero-based page index
	 * @param pageSize   number of employees per page
	 * @return paginated response
	 * @throws CityNotFoundException if no employees exist in that city
	 */
	public PagedResponse<Employee> getEmployeesByCity(String city, int pageNumber, int pageSize) {
		log.debug("getEmployeesByCity(city={}, page={}, size={})", city, pageNumber, pageSize);

		List<Employee> filtered = repository.findAll().stream().filter(e -> e.getCity().equalsIgnoreCase(city))
				.sorted(Comparator.comparing(Employee::getFullName)).collect(Collectors.toList());

		if (filtered.isEmpty()) {
			throw new RuntimeException("No employees found in city: " + city);
		}

		return PaginationUtil.paginate(filtered, pageNumber, pageSize);
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Tool 4 — Get Employees by Country (Paginated)
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Returns paginated employees whose country matches (case-insensitive).
	 *
	 * @param country    the country name to filter by
	 * @param pageNumber zero-based page index
	 * @param pageSize   number of employees per page
	 * @return paginated response
	 * @throws CountryNotFoundException if no employees exist in that country
	 */
	public PagedResponse<Employee> getEmployeesByCountry(String country, int pageNumber, int pageSize) {
		log.debug("getEmployeesByCountry(country={}, page={}, size={})", country, pageNumber, pageSize);

		List<Employee> filtered = repository.findAll().stream().filter(e -> e.getCountry().equalsIgnoreCase(country))
				.sorted(Comparator.comparing(Employee::getFullName)).collect(Collectors.toList());

		if (filtered.isEmpty()) {
			throw new RuntimeException("No employees found in country: " + country);
		}

		return PaginationUtil.paginate(filtered, pageNumber, pageSize);
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Tool 5 — Search Employees
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Full-text search across firstName, lastName, fullName, designation, and
	 * department.
	 *
	 * <p>
	 * The search is case-insensitive and uses {@code contains} matching, so
	 * "senior" will match "Senior Engineer".
	 *
	 * @param keyword the search term
	 * @return matching employees sorted by full name
	 */
	public List<Employee> searchEmployees(String keyword) {
		log.debug("searchEmployees(keyword='{}')", keyword);

		if (keyword == null || keyword.isBlank()) {
			return repository.findAll();
		}

		String q = keyword.toLowerCase();

		return repository.findAll().stream().filter(e -> matches(e, q))
				.sorted(Comparator.comparing(Employee::getFullName)).collect(Collectors.toList());
	}

	/**
	 * Returns true if any searchable field of the employee contains the query
	 * string.
	 */
	private boolean matches(Employee e, String q) {
		return containsIgnoreCase(e.getFirstName(), q) || containsIgnoreCase(e.getLastName(), q)
				|| containsIgnoreCase(e.getFullName(), q) || containsIgnoreCase(e.getDesignation(), q)
				|| containsIgnoreCase(e.getDepartment(), q);
	}

	private boolean containsIgnoreCase(String field, String q) {
		return field != null && field.toLowerCase().contains(q);
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Tool 6 — Get Single Employee
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Returns the complete profile of a single employee by their numeric ID.
	 *
	 * @param employeeId the employee's unique ID
	 * @return the employee
	 * @throws EmployeeNotFoundException if the ID does not exist
	 */
	public Employee getEmployee(Long employeeId) {
		log.debug("getEmployee(id={})", employeeId);

		return repository.findAll().stream().filter(e -> e.getId().equals(employeeId)).findFirst()
				.orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Tool 7 — Get Managers
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Returns all employees who manage at least one other employee.
	 *
	 * <p>
	 * Strategy: collect all unique {@code managerId} values, then return employees
	 * whose ID appears in that set.
	 *
	 * @return list of managers sorted by designation then full name
	 */
	public List<Employee> getManagers() {
		log.debug("getManagers()");

		// Collect all manager IDs (non-null)
		Set<Long> managerIds = repository.findAll().stream().map(Employee::getManagerId).filter(id -> id != null)
				.collect(Collectors.toSet());

		return repository.findAll().stream().filter(e -> managerIds.contains(e.getId()))
				.sorted(Comparator.comparing(Employee::getDesignation).thenComparing(Employee::getFullName))
				.collect(Collectors.toList());
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Tool 8 — Get Departments
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Returns a sorted list of all unique department names.
	 *
	 * @return distinct departments
	 */
	public List<String> getDepartments() {
		log.debug("getDepartments()");

		return repository.findAll().stream().map(Employee::getDepartment).distinct().sorted()
				.collect(Collectors.toList());
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Tool 9 — Get Countries
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Returns a sorted list of all unique country names.
	 *
	 * @return distinct countries
	 */
	public List<String> getCountries() {
		log.debug("getCountries()");

		return repository.findAll().stream().map(Employee::getCountry).distinct().sorted().collect(Collectors.toList());
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Tool 10 — Get Cities
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Returns a sorted list of all unique city names.
	 *
	 * @return distinct cities
	 */
	public List<String> getCities() {
		log.debug("getCities()");

		return repository.findAll().stream().map(Employee::getCity).distinct().sorted().collect(Collectors.toList());
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Tool 11 — Get Employees by Department (Paginated)
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Returns paginated employees in the specified department.
	 *
	 * @param department the department name (case-insensitive)
	 * @param pageNumber zero-based page index
	 * @param pageSize   items per page
	 * @return paginated response
	 * @throws DepartmentNotFoundException if no employees exist in that department
	 */
	public PagedResponse<Employee> getEmployeesByDepartment(String department, int pageNumber, int pageSize) {
		log.debug("getEmployeesByDepartment(dept={}, page={}, size={})", department, pageNumber, pageSize);

		List<Employee> filtered = repository.findAll().stream()
				.filter(e -> e.getDepartment().equalsIgnoreCase(department))
				.sorted(Comparator.comparing(Employee::getFullName)).collect(Collectors.toList());

		if (filtered.isEmpty()) {
			throw new RuntimeException("No employees found in department: " + department);
		}

		return PaginationUtil.paginate(filtered, pageNumber, pageSize);
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Tool 12 — Count Employees
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Returns aggregate headcount statistics.
	 *
	 * @return total, active, and inactive counts
	 */
	public EmployeeCount countEmployees() {
		log.debug("countEmployees()");

		long total = repository.findAll().size();
		long active = repository.findAll().stream().filter(Employee::isActive).count();
		long inactive = total - active;

		EmployeeCount employeeCount = new EmployeeCount();

		employeeCount.setTotalEmployees(total);
		employeeCount.setActiveEmployees(active);
		employeeCount.setInactiveEmployees(inactive);

		return employeeCount;
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Tool 13 — Count Employees by Country
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Returns the number of employees per country, sorted by count descending.
	 *
	 * @return list of country-count pairs
	 */
	public List<CountByGroup> countEmployeesByCountry() {
		log.debug("countEmployeesByCountry()");

		return repository.findAll().stream().collect(Collectors.groupingBy(Employee::getCountry, Collectors.counting()))
				.entrySet().stream().map(e -> new CountByGroup(e.getKey(), e.getValue()))
				.sorted(Comparator.comparingLong(CountByGroup::getCount).reversed()).collect(Collectors.toList());
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Tool 14 — Count Employees by Department
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Returns the number of employees per department, sorted by count descending.
	 *
	 * @return list of department-count pairs
	 */
	public List<CountByGroup> countEmployeesByDepartment() {
		log.debug("countEmployeesByDepartment()");

		return repository.findAll().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting())).entrySet().stream()
				.map(e -> new CountByGroup(e.getKey(), e.getValue()))
				.sorted(Comparator.comparingLong(CountByGroup::getCount).reversed()).collect(Collectors.toList());
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Tool 15 — Get Organization Hierarchy
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Builds the full organization hierarchy as a nested JSON-serializable map.
	 *
	 * <p>
	 * Returns a tree where each node contains:
	 * <ul>
	 * <li>{@code id}, {@code name}, {@code designation}, {@code department}</li>
	 * <li>{@code directReports} — recursive list of subordinates</li>
	 * </ul>
	 *
	 * @return hierarchy rooted at the CEO
	 */
	public Map<String, Object> getOrganizationHierarchy() {
		log.debug("getOrganizationHierarchy()");

		List<Employee> all = repository.findAll();

		// Find the CEO (the employee with no manager)
		Employee ceo = all.stream().filter(e -> e.getManagerId() == null).findFirst()
				.orElseThrow(() -> new IllegalStateException("No CEO found in dataset"));

		return buildNode(ceo, all);
	}

	/**
	 * Recursively builds a hierarchy node for the given employee.
	 *
	 * @param employee     current node
	 * @param allEmployees full flat list used to find children
	 * @return a map representing this employee and their subtree
	 */
	private Map<String, Object> buildNode(Employee employee, List<Employee> allEmployees) {
		// Use LinkedHashMap to preserve insertion order in JSON output
		Map<String, Object> node = new LinkedHashMap<>();
		node.put("id", employee.getId());
		node.put("name", employee.getFullName());
		node.put("designation", employee.getDesignation());
		node.put("department", employee.getDepartment());
		node.put("country", employee.getCountry());
		node.put("city", employee.getCity());

		// Find and recursively build all direct reports
		List<Map<String, Object>> directReports = allEmployees.stream()
				.filter(e -> employee.getId().equals(e.getManagerId()))
				.sorted(Comparator.comparing(Employee::getDesignation).thenComparing(Employee::getFullName))
				.map(e -> buildNode(e, allEmployees)).collect(Collectors.toList());

		node.put("directReports", directReports);
		return node;
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Helper methods used by Resource and Prompt registrars
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Returns all employees in a given department (for resources).
	 *
	 * @param department department name (case-insensitive)
	 * @return matching employees, or empty list if none
	 */
	public List<Employee> findByDepartment(String department) {
		return repository.findAll().stream().filter(e -> e.getDepartment().equalsIgnoreCase(department))
				.sorted(Comparator.comparing(Employee::getFullName)).collect(Collectors.toList());
	}

	/**
	 * Returns all employees in a given country (for resources).
	 *
	 * @param country country name (case-insensitive)
	 * @return matching employees, or empty list if none
	 */
	public List<Employee> findByCountry(String country) {
		return repository.findAll().stream().filter(e -> e.getCountry().equalsIgnoreCase(country))
				.sorted(Comparator.comparing(Employee::getFullName)).collect(Collectors.toList());
	}

	/**
	 * Returns the complete employee list (for resources).
	 *
	 * @return all employees
	 */
	public List<Employee> findAll() {
		return repository.findAll();
	}

	/**
	 * Finds an employee by ID (for prompts), returning {@link Optional}.
	 *
	 * @param id employee ID
	 * @return optional employee
	 */
	public Optional<Employee> findById(Long id) {
		return repository.findAll().stream().filter(e -> e.getId().equals(id)).findFirst();
	}
}
