package com.sample.app.mcp;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Employee MCP Tool Provider
 *
 * Implements MCP tool specifications. Tools represent executable functions
 * that can be invoked to perform actions or retrieve data.
 *
 * Each tool includes:
 * - name: Unique identifier for the tool
 * - description: Purpose and usage documentation
 * - inputSchema: JSON Schema describing required/optional parameters
 */
@Component
public class EmployeeToolProvider {

	private final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Represents a single MCP tool
	 */
	public static class Tool {
		private String name;
		private String description;
		private Map<String, Object> inputSchema;

		public Tool(String name, String description, Map<String, Object> inputSchema) {
			this.name = name;
			this.description = description;
			this.inputSchema = inputSchema;
		}

		public String getName() { return name; }
		public String getDescription() { return description; }
		public Map<String, Object> getInputSchema() { return inputSchema; }
	}

	/**
	 * Represents tool response content
	 */
	public static class ToolResponse {
		private String text;
		private boolean isError;

		public ToolResponse(String text, boolean isError) {
			this.text = text;
			this.isError = isError;
		}

		public String getText() { return text; }
		public boolean isError() { return isError; }
	}

	public List<Tool> tools() {
		List<Tool> tools = new ArrayList<>();

		// Tool 1: Get Employee Details
		tools.add(new Tool(
				"get-employee-details",
				"Retrieve comprehensive details about a specific employee including contact information, role, department, and employment history",
				createSchema(new String[]{"employeeId"})
		));

		// Tool 2: List Department Employees
		tools.add(new Tool(
				"list-department-employees",
				"List all employees in a specific department with their roles and tenure",
				createSchema(new String[]{"departmentId"})
		));

		// Tool 3: Calculate Employee Tenure
		tools.add(new Tool(
				"calculate-employee-tenure",
				"Calculate the tenure of an employee in the organization based on their start date",
				createSchema(new String[]{"employeeId"})
		));

		// Tool 4: Get Team Members
		tools.add(new Tool(
				"get-team-members",
				"Retrieve all members of a specific team including their roles and reporting structure",
				createSchema(new String[]{"teamId"})
		));

		// Tool 5: Search Employees by Skill
		tools.add(new Tool(
				"search-employees-by-skill",
				"Find employees with specific technical or professional skills",
				createSchema(new String[]{"skill"})
		));

		// Tool 6: Get Compensation Info
		tools.add(new Tool(
				"get-compensation-info",
				"Retrieve salary band information for a specific role and level",
				createSchema(new String[]{"role", "level"})
		));

		// Tool 7: Check Leave Balance
		tools.add(new Tool(
				"check-leave-balance",
				"Check remaining leave balance for an employee",
				createSchema(new String[]{"employeeId", "leaveType"})
		));

		// Tool 8: Generate Employee Report
		tools.add(new Tool(
				"generate-employee-report",
				"Generate various employee reports including headcount, turnover, and compensation analysis",
				createSchema(new String[]{"reportType"})
		));

		return tools;
	}

	public ToolResponse call(String name, Map<String, Object> parameters) {
		try {
			return switch (name) {
				case "get-employee-details" -> handleGetEmployeeDetails(parameters);
				case "list-department-employees" -> handleListDepartmentEmployees(parameters);
				case "calculate-employee-tenure" -> handleCalculateEmployeeTenure(parameters);
				case "get-team-members" -> handleGetTeamMembers(parameters);
				case "search-employees-by-skill" -> handleSearchEmployeesBySkill(parameters);
				case "get-compensation-info" -> handleGetCompensationInfo(parameters);
				case "check-leave-balance" -> handleCheckLeaveBalance(parameters);
				case "generate-employee-report" -> handleGenerateEmployeeReport(parameters);
				default -> new ToolResponse("Tool '" + name + "' not found", true);
			};
		} catch (Exception e) {
			return new ToolResponse("Error executing tool: " + e.getMessage(), true);
		}
	}

	// Tool Implementation Methods

	private ToolResponse handleGetEmployeeDetails(Map<String, Object> parameters) throws Exception {
		String employeeId = getString(parameters, "employeeId");

		Map<String, Object> employee = new HashMap<>();
		employee.put("employeeId", employeeId);
		employee.put("name", "John Doe");
		employee.put("email", "john.doe@acmecorp.com");
		employee.put("phone", "+1-555-0123");
		employee.put("role", "Senior Software Engineer");
		employee.put("department", "Engineering");
		employee.put("team", "Backend");
		employee.put("manager", "Alice Johnson");
		employee.put("startDate", "2020-01-15");
		employee.put("employmentType", "Full-time");
		employee.put("location", "San Francisco, CA");
		employee.put("officeType", "Hybrid");

		String content = objectMapper.writeValueAsString(employee);
		return new ToolResponse(content, false);
	}

	private ToolResponse handleListDepartmentEmployees(Map<String, Object> parameters) throws Exception {
		String departmentId = getString(parameters, "departmentId");

		List<Map<String, Object>> employees = new ArrayList<>();
		employees.add(createEmployeeRecord("EMP001", "Alice Johnson", "Engineering Manager", 8));
		employees.add(createEmployeeRecord("EMP002", "Bob Smith", "Senior Developer", 6));
		employees.add(createEmployeeRecord("EMP003", "Carol White", "Frontend Lead", 5));

		Map<String, Object> result = new HashMap<>();
		result.put("departmentId", departmentId);
		result.put("employeeCount", employees.size());
		result.put("employees", employees);

		String content = objectMapper.writeValueAsString(result);
		return new ToolResponse(content, false);
	}

	private ToolResponse handleCalculateEmployeeTenure(Map<String, Object> parameters) throws Exception {
		String employeeId = getString(parameters, "employeeId");
		String startDateStr = (String) parameters.getOrDefault("startDate", "2020-01-15");

		LocalDate startDate = LocalDate.parse(startDateStr);
		LocalDate today = LocalDate.now();
		long years = java.time.temporal.ChronoUnit.YEARS.between(startDate, today);
		long months = java.time.temporal.ChronoUnit.MONTHS.between(startDate, today) % 12;

		Map<String, Object> result = new HashMap<>();
		result.put("employeeId", employeeId);
		result.put("startDate", startDateStr);
		result.put("calculatedDate", today.toString());
		result.put("tenure", Map.of("years", years, "months", months));
		result.put("totalMonths", java.time.temporal.ChronoUnit.MONTHS.between(startDate, today));

		String content = objectMapper.writeValueAsString(result);
		return new ToolResponse(content, false);
	}

	private ToolResponse handleGetTeamMembers(Map<String, Object> parameters) throws Exception {
		String teamId = getString(parameters, "teamId");

		List<Map<String, Object>> members = new ArrayList<>();
		members.add(createEmployeeRecord("EMP002", "Bob Smith", "Senior Developer", 6));
		members.add(createEmployeeRecord("EMP004", "David Chen", "Junior Developer", 2));
		members.add(createEmployeeRecord("EMP005", "Emma Davis", "QA Engineer", 3));

		Map<String, Object> result = new HashMap<>();
		result.put("teamId", teamId);
		result.put("teamName", "Backend Team");
		result.put("lead", "Bob Smith");
		result.put("memberCount", members.size());
		result.put("members", members);

		String content = objectMapper.writeValueAsString(result);
		return new ToolResponse(content, false);
	}

	private ToolResponse handleSearchEmployeesBySkill(Map<String, Object> parameters) throws Exception {
		String skill = getString(parameters, "skill");
		String minimumLevel = (String) parameters.getOrDefault("minimumLevel", "Intermediate");

		List<Map<String, Object>> employees = new ArrayList<>();
		employees.add(Map.of("employeeId", "EMP001", "name", "Alice Johnson", "skillLevel", "Expert"));
		employees.add(Map.of("employeeId", "EMP002", "name", "Bob Smith", "skillLevel", "Advanced"));
		employees.add(Map.of("employeeId", "EMP003", "name", "Carol White", "skillLevel", "Advanced"));

		Map<String, Object> result = new HashMap<>();
		result.put("skill", skill);
		result.put("minimumLevel", minimumLevel);
		result.put("matchCount", employees.size());
		result.put("employees", employees);

		String content = objectMapper.writeValueAsString(result);
		return new ToolResponse(content, false);
	}

	private ToolResponse handleGetCompensationInfo(Map<String, Object> parameters) throws Exception {
		String role = getString(parameters, "role");
		String level = getString(parameters, "level");

		Map<String, Object> compensation = new HashMap<>();
		compensation.put("role", role);
		compensation.put("level", level);
		compensation.put("minSalary", 130000);
		compensation.put("midSalary", 160000);
		compensation.put("maxSalary", 200000);
		compensation.put("bonus", Map.of("target", "20%", "range", "0-30%"));
		compensation.put("equity", "RSU grant based on level");

		String content = objectMapper.writeValueAsString(compensation);
		return new ToolResponse(content, false);
	}

	private ToolResponse handleCheckLeaveBalance(Map<String, Object> parameters) throws Exception {
		String employeeId = getString(parameters, "employeeId");
		String leaveType = getString(parameters, "leaveType");

		Map<String, Object> leaveBalance = new HashMap<>();
		leaveBalance.put("employeeId", employeeId);
		leaveBalance.put("leaveType", leaveType);
		leaveBalance.put("totalAllocation", 20);
		leaveBalance.put("usedDays", 8);
		leaveBalance.put("remainingDays", 12);
		leaveBalance.put("fiscalYear", YearMonth.now().getYear());

		String content = objectMapper.writeValueAsString(leaveBalance);
		return new ToolResponse(content, false);
	}

	private ToolResponse handleGenerateEmployeeReport(Map<String, Object> parameters) throws Exception {
		String reportType = getString(parameters, "reportType");
		String department = (String) parameters.getOrDefault("department", "All");
		String period = (String) parameters.getOrDefault("period", YearMonth.now().getYear() + "-Q1");

		Map<String, Object> report = new HashMap<>();
		report.put("reportType", reportType);
		report.put("department", department);
		report.put("period", period);
		report.put("generatedDate", LocalDate.now().toString());

		switch (reportType) {
			case "headcount":
				report.put("totalHeadcount", 150);
				report.put("fullTime", 140);
				report.put("contractors", 10);
				report.put("byDepartment", Map.of("Engineering", 60, "Sales", 40, "HR", 15, "Other", 35));
				break;
			case "turnover":
				report.put("turnoverRate", "8.5%");
				report.put("newHires", 15);
				report.put("departures", 12);
				report.put("avgTenure", "3.2 years");
				break;
			case "compensation":
				report.put("totalCompensationCost", "$12.5M");
				report.put("avgBaseSalary", "$95,000");
				report.put("medianBonus", "$18,000");
				break;
			case "engagement":
				report.put("engagementScore", "8.2/10");
				report.put("retentionRate", "91.5%");
				report.put("responseRate", "87%");
				break;
		}

		String content = objectMapper.writeValueAsString(report);
		return new ToolResponse(content, false);
	}

	// Helper Methods

	private Map<String, Object> createEmployeeRecord(String id, String name, String role, int tenure) {
		return Map.of(
				"employeeId", id,
				"name", name,
				"role", role,
				"tenure", tenure + " years",
				"status", "Active");
	}

	private String getString(Map<String, Object> parameters, String key) {
		Object value = parameters.get(key);
		if (value == null) {
			throw new IllegalArgumentException("Required parameter '" + key + "' is missing");
		}
		return value.toString();
	}

	private Map<String, Object> createSchema(String[] requiredFields) {
		Map<String, Object> schema = new HashMap<>();
		schema.put("type", "object");

		Map<String, Object> properties = new HashMap<>();
		for (String field : requiredFields) {
			Map<String, Object> fieldDef = new HashMap<>();
			fieldDef.put("type", "string");
			properties.put(field, fieldDef);
		}
		schema.put("properties", properties);
		schema.put("required", List.of(requiredFields));

		return schema;
	}

}
