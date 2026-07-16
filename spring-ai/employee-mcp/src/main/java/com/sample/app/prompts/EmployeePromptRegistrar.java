package com.sample.app.prompts;

import io.modelcontextprotocol.server.McpStatelessServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sample.app.model.Employee;
import com.sample.app.service.EmployeeService;

/**
 * Registers all MCP Prompts for the Employee MCP Server.
 *
 * <p>
 * <b>What are MCP Prompts?</b> Prompts are reusable message templates that
 * guide AI behavior. Unlike tools (which execute code) or resources (which
 * return static data), prompts generate structured conversation messages that
 * tell the AI <em>how to behave</em> for a given context.
 *
 * <p>
 * <b>When the AI calls a prompt:</b>
 * <ol>
 * <li>The client sends a {@code prompts/get} request with the prompt name and
 * arguments</li>
 * <li>The handler uses arguments to build dynamic context (looking up employee
 * data)</li>
 * <li>It returns a list of {@link McpSchema.PromptMessage} objects (SYSTEM +
 * USER messages)</li>
 * <li>The AI uses these messages as the starting context for its response</li>
 * </ol>
 *
 * <p>
 * <b>Prompts registered (7 total):</b>
 * <ul>
 * <li>{@code employee-summary} — Professional summary for a specific
 * employee</li>
 * <li>{@code department-summary} — Overview of a department's team</li>
 * <li>{@code country-summary} — Geographic workforce summary</li>
 * <li>{@code manager-summary} — Manager's team overview</li>
 * <li>{@code hr-assistant} — Reusable HR assistant persona</li>
 * <li>{@code employee-search-assistant} — Guided search assistant</li>
 * <li>{@code organization-explorer} — Org hierarchy navigation assistant</li>
 * </ul>
 */
@Configuration
public class EmployeePromptRegistrar {
	private static final Logger log = LoggerFactory.getLogger(EmployeePromptRegistrar.class);

	private final EmployeeService employeeService;

	public EmployeePromptRegistrar(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * Registers all 7 MCP Prompt specifications as a single bean.
	 *
	 * @return list of prompt specifications
	 */
	@Bean
	public List<McpStatelessServerFeatures.SyncPromptSpecification> employeePrompts() {
		return List.of(employeeSummaryPrompt(), departmentSummaryPrompt(), countrySummaryPrompt(),
				managerSummaryPrompt(), hrAssistantPrompt(), employeeSearchAssistantPrompt(),
				organizationExplorerPrompt());
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Prompt 1 — Employee Summary
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Generates a professional summary prompt for a specific employee. Looks up the
	 * employee by ID and fills in real data.
	 */
	private McpStatelessServerFeatures.SyncPromptSpecification employeeSummaryPrompt() {

		var prompt = new McpSchema.Prompt("employee-summary", "Generate a professional summary for a specific employee",
				List.of(new McpSchema.PromptArgument("employeeId", "The numeric ID of the employee (e.g., 42)",
						Boolean.TRUE)));

		return new McpStatelessServerFeatures.SyncPromptSpecification(prompt, (ctx, req) -> {
			String employeeIdStr = getArg(req.arguments(), "employeeId", "1");
			Long employeeId = Long.parseLong(employeeIdStr.trim());

			log.debug("[PROMPT] employee-summary for employeeId={}", employeeId);

			Optional<Employee> employeeOpt = employeeService.findById(employeeId);
			if (employeeOpt.isEmpty()) {
				return errorPromptResult("Employee with ID " + employeeId + " not found.");
			}
			Employee emp = employeeOpt.get();

			String systemMessage = """
					You are an experienced HR professional generating employee profiles.
					Write in a formal, positive tone. Structure your response as:
					1. Professional Summary (2-3 sentences)
					2. Role & Responsibilities (bullet points)
					3. Skills Overview (inferred from designation)
					4. Career Path (based on hierarchy level)
					""";

			String userMessage = String.format("""
					Generate a professional summary for the following employee:

					Name:        %s
					Employee #:  %s
					Designation: %s
					Department:  %s
					Location:    %s, %s, %s
					Manager:     %s
					Joined:      %s
					Status:      %s

					Please provide a structured professional summary.
					""", emp.getFullName(), emp.getEmployeeNumber(), emp.getDesignation(), emp.getDepartment(),
					emp.getCity(), emp.getState(), emp.getCountry(),
					emp.getManagerName() != null ? emp.getManagerName() : "N/A (CEO)", emp.getJoiningDate(),
					emp.isActive() ? "Active" : "Inactive");

			return buildPromptResult("Employee Professional Summary for " + emp.getFullName(), systemMessage,
					userMessage);
		});
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Prompt 2 — Department Summary
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Generates a department overview prompt with real team data.
	 */
	private McpStatelessServerFeatures.SyncPromptSpecification departmentSummaryPrompt() {

		var prompt = new McpSchema.Prompt("department-summary",
				"Generate a department overview including team size, managers, and distribution",
				List.of(new McpSchema.PromptArgument("department", "Department name (e.g., Engineering, Sales, HR)",
						Boolean.TRUE)));

		return new McpStatelessServerFeatures.SyncPromptSpecification(prompt, (ctx, req) -> {
			String department = getArg(req.arguments(), "department", "Engineering");
			log.debug("[PROMPT] department-summary for department={}", department);

			List<Employee> members = employeeService.findByDepartment(department);
			if (members.isEmpty()) {
				return errorPromptResult("Department '" + department
						+ "' not found. Use getDepartments() to see available departments.");
			}

			long active = members.stream().filter(Employee::isActive).count();
			long inactive = members.size() - active;

			List<Employee> managers = employeeService.getManagers().stream()
					.filter(m -> m.getDepartment().equalsIgnoreCase(department)).collect(Collectors.toList());

			List<String> cities = members.stream().map(Employee::getCity).distinct().sorted()
					.collect(Collectors.toList());
			List<String> designations = members.stream().map(Employee::getDesignation).distinct().sorted()
					.collect(Collectors.toList());

			String systemMessage = """
					You are an HR analyst preparing a department overview report.
					Be concise, data-driven, and professional.
					Structure your response as:
					1. Department Overview
					2. Team Size & Composition
					3. Leadership
					4. Geographic Distribution
					5. Key Observations
					""";

			String userMessage = String.format("""
					Generate a department summary for: %s

					Team Size:    %d total (%d active, %d inactive)
					Managers:     %s
					Office Locations: %s
					Designations: %s

					Please provide a structured department overview.
					""", department, members.size(), active, inactive,
					managers.stream().map(Employee::getFullName).collect(Collectors.joining(", ")),
					String.join(", ", cities), String.join(", ", designations));

			return buildPromptResult("Department Summary: " + department, systemMessage, userMessage);
		});
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Prompt 3 — Country Summary
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Generates a geographic workforce summary for a country.
	 */
	private McpStatelessServerFeatures.SyncPromptSpecification countrySummaryPrompt() {

		var prompt = new McpSchema.Prompt("country-summary", "Generate a workforce summary for a specific country", List
				.of(new McpSchema.PromptArgument("country", "Country name (e.g., India, USA, Germany)", Boolean.TRUE)));

		return new McpStatelessServerFeatures.SyncPromptSpecification(prompt, (ctx, req) -> {
			String country = getArg(req.arguments(), "country", "India");
			log.debug("[PROMPT] country-summary for country={}", country);

			List<Employee> employees = employeeService.findByCountry(country);
			if (employees.isEmpty()) {
				return errorPromptResult(
						"Country '" + country + "' not found. Use getCountries() to see available countries.");
			}

			List<String> depts = employees.stream().map(Employee::getDepartment).distinct().sorted()
					.collect(Collectors.toList());
			List<String> cities = employees.stream().map(Employee::getCity).distinct().sorted()
					.collect(Collectors.toList());
			long active = employees.stream().filter(Employee::isActive).count();

			String systemMessage = """
					You are a Global HR Manager preparing a country workforce report.
					Focus on team composition, departments present, and geographic spread.
					""";

			String userMessage = String.format("""
					Generate a workforce summary for: %s

					Total Employees: %d (%d active)
					Departments:     %s
					Cities:          %s

					Provide insights about the workforce in %s.
					""", country, employees.size(), active, String.join(", ", depts), String.join(", ", cities),
					country);

			return buildPromptResult("Country Workforce Summary: " + country, systemMessage, userMessage);
		});
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Prompt 4 — Manager Summary
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Generates a manager's team overview with their direct reports listed.
	 */
	private McpStatelessServerFeatures.SyncPromptSpecification managerSummaryPrompt() {

		var prompt = new McpSchema.Prompt("manager-summary",
				"Generate a team overview for a specific manager including all direct reports",
				List.of(new McpSchema.PromptArgument("managerId", "The numeric ID of the manager", Boolean.TRUE)));

		return new McpStatelessServerFeatures.SyncPromptSpecification(prompt, (ctx, req) -> {
			String managerIdStr = getArg(req.arguments(), "managerId", "8");
			Long managerId = Long.parseLong(managerIdStr.trim());

			log.debug("[PROMPT] manager-summary for managerId={}", managerId);

			Optional<Employee> managerOpt = employeeService.findById(managerId);
			if (managerOpt.isEmpty()) {
				return errorPromptResult("Manager with ID " + managerId + " not found.");
			}
			Employee manager = managerOpt.get();

			List<Employee> reports;
			try {
				reports = employeeService.getEmployeesUnderManager(managerId);
			} catch (Exception e) {
				reports = List.of();
			}

			List<String> departments = reports.stream().map(Employee::getDepartment).distinct()
					.collect(Collectors.toList());

			String systemMessage = """
					You are an HR analyst preparing a manager's team profile.
					Summarize the team composition, skills, and geographic spread.
					""";

			String userMessage = String.format("""
					Generate a team summary for Manager: %s (%s)

					Manager Details:
					  ID:          %d
					  Designation: %s
					  Department:  %s
					  Location:    %s, %s

					Team Size: %d direct reports
					Departments in team: %s

					Team Members:
					%s

					Please summarize this manager's team.
					""", manager.getFullName(), manager.getEmployeeNumber(), manager.getId(), manager.getDesignation(),
					manager.getDepartment(), manager.getCity(), manager.getCountry(), reports.size(),
					String.join(", ", departments),
					reports.stream()
							.map(e -> "  - " + e.getFullName() + " (" + e.getDesignation() + ", " + e.getCity() + ")")
							.collect(Collectors.joining("\n")));

			return buildPromptResult("Manager Team Summary: " + manager.getFullName(), systemMessage, userMessage);
		});
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Prompt 5 — HR Assistant (Persona)
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Establishes an HR Assistant persona that answers only from MCP data.
	 */
	private McpStatelessServerFeatures.SyncPromptSpecification hrAssistantPrompt() {

		var prompt = new McpSchema.Prompt("hr-assistant",
				"Activates an HR Assistant that answers questions using only the MCP employee data", List.of() // No
																												// arguments
																												// —
																												// this
																												// is a
																												// persona
																												// prompt
		);

		return new McpStatelessServerFeatures.SyncPromptSpecification(prompt, (ctx, req) -> {
			log.debug("[PROMPT] hr-assistant activated");

			var count = employeeService.countEmployees();
			List<String> depts = employeeService.getDepartments();
			List<String> countries = employeeService.getCountries();

			String systemMessage = String.format("""
					You are an intelligent HR Assistant for AcmeCorp.
					You ONLY answer questions using information available through the MCP resources and tools.
					If asked about something outside the employee data, politely decline.

					Organization facts you know:
					- Total Employees: %d (%d active, %d inactive)
					- Departments: %s
					- Countries of operation: %s
					- Hierarchy: CEO → VP → Director → Manager → Individual Contributors

					Available MCP Tools you can call to answer questions:
					- getEmployee(id), getEmployees(page, size)
					- getEmployeesByDepartment, getEmployeesByCountry, getEmployeesByCity
					- getEmployeesUnderManager(managerId)
					- searchEmployees(keyword)
					- countEmployees(), countEmployeesByCountry(), countEmployeesByDepartment()
					- getOrganizationHierarchy()

					Always be helpful, accurate, and professional.
					""", count.getTotalEmployees(), count.getActiveEmployees(), count.getInactiveEmployees(),
					String.join(", ", depts), String.join(", ", countries));

			String userMessage = "Hello! I'm ready to answer questions about AcmeCorp's employees. What would you like to know?";

			return buildPromptResult("HR Assistant", systemMessage, userMessage);
		});
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Prompt 6 — Employee Search Assistant
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * A guided assistant that helps AI clients effectively use the search tools.
	 */
	private McpStatelessServerFeatures.SyncPromptSpecification employeeSearchAssistantPrompt() {

		var prompt = new McpSchema.Prompt("employee-search-assistant",
				"Activates a search guide that helps find employees using the right MCP tools", List.of() // No
																											// arguments
		);

		return new McpStatelessServerFeatures.SyncPromptSpecification(prompt, (ctx, req) -> {
			log.debug("[PROMPT] employee-search-assistant activated");

			String systemMessage = """
					You are an Employee Search Assistant for AcmeCorp.
					Your job is to help users find the right employees using the available MCP tools.

					Search strategy:
					1. If searching by NAME or ROLE: use searchEmployees(keyword)
					2. If searching by CITY: use getEmployeesByCity(city, page, size)
					3. If searching by COUNTRY: use getEmployeesByCountry(country, page, size)
					4. If searching by DEPARTMENT: use getEmployeesByDepartment(dept, page, size)
					5. If looking for MANAGERS: use getManagers()
					6. If looking for REPORTS: use getEmployeesUnderManager(managerId)
					7. If browsing all: use getEmployees(page, size)
					8. For single profile: use getEmployee(employeeId)

					Always confirm the search type before calling a tool.
					If results are too many, suggest narrowing with filters.
					Pagination starts at page 0.
					""";

			String userMessage = "I'm ready to help you search for employees. What are you looking for — name, role, location, or department?";

			return buildPromptResult("Employee Search Assistant", systemMessage, userMessage);
		});
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Prompt 7 — Organization Explorer
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * An assistant that helps navigate the org hierarchy interactively.
	 */
	private McpStatelessServerFeatures.SyncPromptSpecification organizationExplorerPrompt() {

		var prompt = new McpSchema.Prompt("organization-explorer",
				"Navigate the organization hierarchy interactively from CEO down to individual contributors", List.of() // No
																														// arguments
		);

		return new McpStatelessServerFeatures.SyncPromptSpecification(prompt, (ctx, req) -> {
			log.debug("[PROMPT] organization-explorer activated");

			String systemMessage = """
					You are an Organization Explorer for AcmeCorp.
					Help users navigate and understand the company's hierarchy.

					Hierarchy structure:
					Level 1: CEO (Robert Mitchell, id=1)
					Level 2: Vice Presidents (Engineering, Finance, Sales — ids 2,3,4)
					Level 3: Directors (9 directors — ids 5-7, 14-16, 23-25)
					Level 4: Managers (18 managers — ids 8-13, 17-22, 26-31)
					Level 5: Individual Contributors (ids 32-100)

					Exploration tools:
					- getOrganizationHierarchy() : Full tree (use sparingly — large response)
					- getManagers()              : All people with direct reports
					- getEmployeesUnderManager(id) : Direct reports of any person
					- getEmployee(id)            : Profile of any person

					Tips for navigation:
					- Start with getManagers() to see the leadership map
					- Drill down with getEmployeesUnderManager(managerId)
					- Use getEmployee(id) for detailed profiles
					""";

			String userMessage = "Ready to explore AcmeCorp's organization chart! Where would you like to start — the executive team, a specific department, or a particular person?";

			return buildPromptResult("Organization Explorer", systemMessage, userMessage);
		});
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Helper methods
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Builds a standard two-message prompt result (SYSTEM + USER).
	 *
	 * @param description short description of the prompt context
	 * @param systemText  the SYSTEM role message
	 * @param userText    the USER role message
	 * @return a {@link McpSchema.GetPromptResult}
	 */
	private McpSchema.GetPromptResult buildPromptResult(String description, String systemText, String userText) {
		return new McpSchema.GetPromptResult(description,
				List.of(new McpSchema.PromptMessage(McpSchema.Role.USER,
						new McpSchema.TextContent("SYSTEM: " + systemText.strip())),
						new McpSchema.PromptMessage(McpSchema.Role.USER, new McpSchema.TextContent(userText.strip()))));
	}

	/**
	 * Builds an error prompt result when a required argument is invalid.
	 *
	 * @param errorMessage the human-readable error explanation
	 * @return a prompt result communicating the error
	 */
	private McpSchema.GetPromptResult errorPromptResult(String errorMessage) {
		return new McpSchema.GetPromptResult("Error", List.of(
				new McpSchema.PromptMessage(McpSchema.Role.USER, new McpSchema.TextContent("Error: " + errorMessage))));
	}

	/**
	 * Safely extracts a named argument from the prompt arguments map.
	 *
	 * <p>
	 * Note: {@code McpSchema.GetPromptRequest.arguments()} returns
	 * {@code Map<String, Object>} because the MCP spec allows any JSON value as an
	 * argument. We coerce to String here.
	 *
	 * @param arguments    the arguments map from the prompt request (may be null)
	 * @param key          the argument key
	 * @param defaultValue fallback value if the key is absent or null
	 * @return the argument value as a String, or the default
	 */
	private String getArg(Map<String, Object> arguments, String key, String defaultValue) {
		if (arguments == null)
			return defaultValue;
		Object value = arguments.get(key);
		if (value == null)
			return defaultValue;
		String str = value.toString();
		return !str.isBlank() ? str : defaultValue;
	}
}
