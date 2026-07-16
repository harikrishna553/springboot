package com.sample.app.tools;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import com.sample.app.model.CountByGroup;
import com.sample.app.model.Employee;
import com.sample.app.model.EmployeeCount;
import com.sample.app.model.PagedResponse;
import com.sample.app.service.EmployeeService;

/**
 * Exposes all 15 employee management capabilities as MCP Tools via Spring AI's
 * {@link Tool @Tool} annotation.
 *
 * <p>
 * <b>How @Tool works:</b>
 * <ol>
 * <li>Spring AI scans this bean for {@code @Tool}-annotated methods at
 * startup</li>
 * <li>It generates JSON Schema from method signatures and {@code @ToolParam}
 * descriptions</li>
 * <li>The schema is served to AI clients via {@code tools/list}</li>
 * <li>When an AI calls a tool, Spring AI deserializes parameters and invokes
 * the method</li>
 * <li>The typed return value is serialized to JSON (once — no
 * double-encoding)</li>
 * </ol>
 *
 * <p>
 * <b>Error handling:</b> Exceptions thrown here bubble to Spring AI, which
 * converts them into MCP error responses. Do NOT use {@code @ControllerAdvice}
 * — it does not intercept MCP tool execution.
 *
 * <p>
 * <b>Return types:</b> Always return typed DTOs, never raw JSON strings. Spring
 * AI serializes the object exactly once, avoiding double-encoding.
 */
@Service
public class EmployeeTools {

	private static final Logger log = LoggerFactory.getLogger(EmployeeTools.class);

	private final EmployeeService employeeService;

	/**
	 * Constructor injection.
	 *
	 * @param employeeService the business logic layer
	 */
	public EmployeeTools(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// ═══════════════════════════════════════════════════════════════════════
	// Tool 1 — getEmployees
	// ═══════════════════════════════════════════════════════════════════════

	/**
	 * MCP Tool: Retrieve a paginated list of all employees.
	 */
	@Tool(name = "getEmployees", description = """
			Retrieve a paginated list of all employees in the organization.
			Pagination is zero-indexed: pageNumber=0 is the first page.

			Returns: currentPage, pageSize, totalPages, totalElements, and the employee list.

			Use this tool to browse the employee directory when no specific filter is needed.
			""")
	public PagedResponse<Employee> getEmployees(
			@ToolParam(description = "Zero-based page number (0 = first page). Default: 0.") int pageNumber,
			@ToolParam(description = "Number of employees to return per page (1–100). Default: 10.") int pageSize) {

		long start = System.currentTimeMillis();
		log.info("[TOOL] getEmployees called — page={}, size={}", pageNumber, pageSize);

		PagedResponse<Employee> result = employeeService.getEmployees(pageNumber, pageSize);

		log.info("[TOOL] getEmployees returned {} employees (total={}) in {}ms", result.getData().size(),
				result.getTotalElements(), System.currentTimeMillis() - start);
		return result;
	}

	// ═══════════════════════════════════════════════════════════════════════
	// Tool 2 — getEmployeesUnderManager
	// ═══════════════════════════════════════════════════════════════════════

	/**
	 * MCP Tool: Get all direct reports of a given manager.
	 */
	@Tool(name = "getEmployeesUnderManager", description = """
			Get all employees who directly report to the specified manager.

			Returns every employee whose managerId equals the given managerId.
			Use getManagers() first to discover valid manager IDs.
			""")
	public List<Employee> getEmployeesUnderManager(
			@ToolParam(description = "The numeric ID of the manager (e.g., 8 for Vikram Rao).") Long managerId) {

		long start = System.currentTimeMillis();
		log.info("[TOOL] getEmployeesUnderManager called — managerId={}", managerId);

		List<Employee> result = employeeService.getEmployeesUnderManager(managerId);

		log.info("[TOOL] getEmployeesUnderManager returned {} direct reports in {}ms", result.size(),
				System.currentTimeMillis() - start);
		return result;
	}

	// ═══════════════════════════════════════════════════════════════════════
	// Tool 3 — getEmployeesByCity
	// ═══════════════════════════════════════════════════════════════════════

	/**
	 * MCP Tool: Get paginated employees in a given city.
	 */
	@Tool(name = "getEmployeesByCity", description = """
			Retrieve a paginated list of employees located in the specified city.
			The city name comparison is case-insensitive (e.g., "bangalore" = "Bangalore").

			Use getCities() to discover all available city names.
			""")
	public PagedResponse<Employee> getEmployeesByCity(
			@ToolParam(description = "City name to filter by (e.g., 'Bangalore', 'New York', 'Tokyo').") String city,
			@ToolParam(description = "Zero-based page number. Default: 0.") int pageNumber,
			@ToolParam(description = "Number of results per page (1–100). Default: 10.") int pageSize) {

		long start = System.currentTimeMillis();
		log.info("[TOOL] getEmployeesByCity called — city={}, page={}, size={}", city, pageNumber, pageSize);

		PagedResponse<Employee> result = employeeService.getEmployeesByCity(city, pageNumber, pageSize);

		log.info("[TOOL] getEmployeesByCity returned {} employees in {}ms", result.getTotalElements(),
				System.currentTimeMillis() - start);
		return result;
	}

	// ═══════════════════════════════════════════════════════════════════════
	// Tool 4 — getEmployeesByCountry
	// ═══════════════════════════════════════════════════════════════════════

	/**
	 * MCP Tool: Get paginated employees in a given country.
	 */
	@Tool(name = "getEmployeesByCountry", description = """
			Retrieve a paginated list of employees located in the specified country.
			The country name comparison is case-insensitive.

			Use getCountries() to discover all available country names.
			Available countries: India, USA, Germany, Japan, Australia, UK, Canada, Singapore.
			""")
	public PagedResponse<Employee> getEmployeesByCountry(
			@ToolParam(description = "Country name to filter by (e.g., 'India', 'USA', 'Germany').") String country,
			@ToolParam(description = "Zero-based page number. Default: 0.") int pageNumber,
			@ToolParam(description = "Number of results per page (1–100). Default: 10.") int pageSize) {

		long start = System.currentTimeMillis();
		log.info("[TOOL] getEmployeesByCountry called — country={}, page={}, size={}", country, pageNumber, pageSize);

		PagedResponse<Employee> result = employeeService.getEmployeesByCountry(country, pageNumber, pageSize);

		log.info("[TOOL] getEmployeesByCountry returned {} employees in {}ms", result.getTotalElements(),
				System.currentTimeMillis() - start);
		return result;
	}

	// ═══════════════════════════════════════════════════════════════════════
	// Tool 5 — searchEmployees
	// ═══════════════════════════════════════════════════════════════════════

	/**
	 * MCP Tool: Keyword search across employee fields.
	 */
	@Tool(name = "searchEmployees", description = """
			Search for employees by keyword across multiple fields:
			firstName, lastName, fullName, designation, and department.

			The search is case-insensitive and uses partial matching.
			Examples:
			  - "senior"    → all Senior Engineers
			  - "architect" → all Architects
			  - "sharma"    → employees with "Sharma" in their name
			  - "bangalore" → does NOT work — use getEmployeesByCity for city search
			""")
	public List<Employee> searchEmployees(
			@ToolParam(description = "Search keyword (matches against name, designation, department).") String keyword) {

		long start = System.currentTimeMillis();
		log.info("[TOOL] searchEmployees called — keyword='{}'", keyword);

		List<Employee> result = employeeService.searchEmployees(keyword);

		log.info("[TOOL] searchEmployees returned {} matches for '{}' in {}ms", result.size(), keyword,
				System.currentTimeMillis() - start);
		return result;
	}

	// ═══════════════════════════════════════════════════════════════════════
	// Tool 6 — getEmployee
	// ═══════════════════════════════════════════════════════════════════════

	/**
	 * MCP Tool: Get a single employee by ID.
	 */
	@Tool(name = "getEmployee", description = """
			Retrieve the complete profile of a single employee by their numeric ID.
			Returns all fields: id, name, email, phone, designation, department,
			location, manager info, salary, joining date, and active status.

			Use getEmployees() to discover valid employee IDs.
			""")
	public Employee getEmployee(
			@ToolParam(description = "The unique numeric employee ID (e.g., 42).") Long employeeId) {

		long start = System.currentTimeMillis();
		log.info("[TOOL] getEmployee called — employeeId={}", employeeId);

		Employee result = employeeService.getEmployee(employeeId);

		log.info("[TOOL] getEmployee returned '{}' in {}ms", result.getFullName(), System.currentTimeMillis() - start);
		return result;
	}

	// ═══════════════════════════════════════════════════════════════════════
	// Tool 7 — getManagers
	// ═══════════════════════════════════════════════════════════════════════

	/**
	 * MCP Tool: Get all employees who manage at least one other employee.
	 */
	@Tool(name = "getManagers", description = """
			Returns all employees who have at least one direct report.
			Includes managers at all levels: CEO, VP, Director, and Manager.

			Useful before calling getEmployeesUnderManager() to know valid manager IDs.
			""")
	public List<Employee> getManagers() {

		long start = System.currentTimeMillis();
		log.info("[TOOL] getManagers called");

		List<Employee> result = employeeService.getManagers();

		log.info("[TOOL] getManagers returned {} managers in {}ms", result.size(), System.currentTimeMillis() - start);
		return result;
	}

	// ═══════════════════════════════════════════════════════════════════════
	// Tool 8 — getDepartments
	// ═══════════════════════════════════════════════════════════════════════

	/**
	 * MCP Tool: Get all unique department names.
	 */
	@Tool(name = "getDepartments", description = """
			Returns a sorted list of all unique department names in the organization.
			Use these values as input for getEmployeesByDepartment() and countEmployeesByDepartment().
			""")
	public List<String> getDepartments() {

		log.info("[TOOL] getDepartments called");
		List<String> result = employeeService.getDepartments();
		log.info("[TOOL] getDepartments returned {} departments", result.size());
		return result;
	}

	// ═══════════════════════════════════════════════════════════════════════
	// Tool 9 — getCountries
	// ═══════════════════════════════════════════════════════════════════════

	/**
	 * MCP Tool: Get all unique country names.
	 */
	@Tool(name = "getCountries", description = """
			Returns a sorted list of all unique country names where the company has employees.
			Use these values as input for getEmployeesByCountry() and countEmployeesByCountry().
			""")
	public List<String> getCountries() {

		log.info("[TOOL] getCountries called");
		List<String> result = employeeService.getCountries();
		log.info("[TOOL] getCountries returned {} countries", result.size());
		return result;
	}

	// ═══════════════════════════════════════════════════════════════════════
	// Tool 10 — getCities
	// ═══════════════════════════════════════════════════════════════════════

	/**
	 * MCP Tool: Get all unique city names.
	 */
	@Tool(name = "getCities", description = """
			Returns a sorted list of all unique city names where the company has offices.
			Use these values as input for getEmployeesByCity().
			""")
	public List<String> getCities() {

		log.info("[TOOL] getCities called");
		List<String> result = employeeService.getCities();
		log.info("[TOOL] getCities returned {} cities", result.size());
		return result;
	}

	// ═══════════════════════════════════════════════════════════════════════
	// Tool 11 — getEmployeesByDepartment
	// ═══════════════════════════════════════════════════════════════════════

	/**
	 * MCP Tool: Get paginated employees in a department.
	 */
	@Tool(name = "getEmployeesByDepartment", description = """
			Retrieve a paginated list of employees in the specified department.
			The department name comparison is case-insensitive.

			Use getDepartments() to discover all available department names.
			Available departments: Engineering, Finance, Sales, HR, Marketing,
			Support, Operations, Legal, Security, Executive.
			""")
	public PagedResponse<Employee> getEmployeesByDepartment(
			@ToolParam(description = "Department name to filter by (e.g., 'Engineering', 'Sales').") String department,
			@ToolParam(description = "Zero-based page number. Default: 0.") int pageNumber,
			@ToolParam(description = "Number of results per page (1–100). Default: 10.") int pageSize) {

		long start = System.currentTimeMillis();
		log.info("[TOOL] getEmployeesByDepartment called — dept={}, page={}, size={}", department, pageNumber,
				pageSize);

		PagedResponse<Employee> result = employeeService.getEmployeesByDepartment(department, pageNumber, pageSize);

		log.info("[TOOL] getEmployeesByDepartment returned {} employees in {}ms", result.getTotalElements(),
				System.currentTimeMillis() - start);
		return result;
	}

	// ═══════════════════════════════════════════════════════════════════════
	// Tool 12 — countEmployees
	// ═══════════════════════════════════════════════════════════════════════

	/**
	 * MCP Tool: Get total, active, and inactive employee counts.
	 */
	@Tool(name = "countEmployees", description = """
			Returns aggregate headcount statistics for the entire organization:
			  - totalEmployees   : total headcount
			  - activeEmployees  : currently active employees
			  - inactiveEmployees: employees who are inactive (on leave / terminated)
			""")
	public EmployeeCount countEmployees() {

		log.info("[TOOL] countEmployees called");
		EmployeeCount result = employeeService.countEmployees();
		log.info("[TOOL] countEmployees — total={}, active={}, inactive={}", result.getTotalEmployees(),
				result.getActiveEmployees(), result.getInactiveEmployees());
		return result;
	}

	// ═══════════════════════════════════════════════════════════════════════
	// Tool 13 — countEmployeesByCountry
	// ═══════════════════════════════════════════════════════════════════════

	/**
	 * MCP Tool: Get employee count per country.
	 */
	@Tool(name = "countEmployeesByCountry", description = """
			Returns the number of employees per country, sorted by count descending.
			Each entry contains: group (country name) and count.

			Useful for understanding the geographic distribution of the workforce.
			""")
	public List<CountByGroup> countEmployeesByCountry() {

		log.info("[TOOL] countEmployeesByCountry called");
		List<CountByGroup> result = employeeService.countEmployeesByCountry();
		log.info("[TOOL] countEmployeesByCountry returned {} country groups", result.size());
		return result;
	}

	// ═══════════════════════════════════════════════════════════════════════
	// Tool 14 — countEmployeesByDepartment
	// ═══════════════════════════════════════════════════════════════════════

	/**
	 * MCP Tool: Get employee count per department.
	 */
	@Tool(name = "countEmployeesByDepartment", description = """
			Returns the number of employees per department, sorted by count descending.
			Each entry contains: group (department name) and count.

			Useful for understanding team sizes and organizational structure.
			""")
	public List<CountByGroup> countEmployeesByDepartment() {

		log.info("[TOOL] countEmployeesByDepartment called");
		List<CountByGroup> result = employeeService.countEmployeesByDepartment();
		log.info("[TOOL] countEmployeesByDepartment returned {} department groups", result.size());
		return result;
	}

	// ═══════════════════════════════════════════════════════════════════════
	// Tool 15 — getOrganizationHierarchy
	// ═══════════════════════════════════════════════════════════════════════

	/**
	 * MCP Tool: Get the complete org hierarchy as a nested tree.
	 */
	@Tool(name = "getOrganizationHierarchy", description = """
			Returns the full organization hierarchy as a nested JSON tree.
			Starts at the CEO and recursively includes all levels:
			  CEO → VP → Director → Manager → Individual Contributors

			Each node contains: id, name, designation, department, country, city,
			and a directReports array with nested subtrees.

			Warning: this returns the entire tree. For targeted queries,
			use getEmployeesUnderManager() with a specific manager ID instead.
			""")
	public Map<String, Object> getOrganizationHierarchy() {

		long start = System.currentTimeMillis();
		log.info("[TOOL] getOrganizationHierarchy called");

		Map<String, Object> result = employeeService.getOrganizationHierarchy();

		log.info("[TOOL] getOrganizationHierarchy built in {}ms", System.currentTimeMillis() - start);
		return result;
	}
}
