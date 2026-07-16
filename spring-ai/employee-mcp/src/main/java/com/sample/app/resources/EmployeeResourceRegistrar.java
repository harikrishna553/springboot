package com.sample.app.resources;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sample.app.model.Employee;
import com.sample.app.service.EmployeeService;

import io.modelcontextprotocol.server.McpStatelessServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;

/**
 * Registers all MCP Resources for the Employee MCP Server.
 *
 * <p>
 * <b>What are MCP Resources?</b> Resources are static or semi-static data
 * endpoints identified by a URI scheme. Unlike tools (which are callable
 * functions), resources represent data the AI can "read" without providing
 * parameters. Think of them as bookmarks to pre-aggregated views.
 *
 * <p>
 * <b>How registration works in STATELESS mode:</b>
 * <ol>
 * <li>Each resource is a
 * {@link McpStatelessServerFeatures.SyncResourceSpecification}</li>
 * <li>It combines a {@link McpSchema.Resource} (URI + metadata) with a read
 * handler</li>
 * <li>The read handler:
 * {@code BiFunction<McpTransportContext, ReadResourceRequest, ReadResourceResult>}</li>
 * <li>We expose all registrations as a single {@code List<>} bean — the
 * autoconfigure picks it up</li>
 * </ol>
 *
 * <p>
 * <b>Resources registered (15 total):</b>
 * <ul>
 * <li>{@code employees://all} — Full employee list</li>
 * <li>{@code employees://countries} — All countries</li>
 * <li>{@code employees://cities} — All cities</li>
 * <li>{@code employees://departments} — All departments</li>
 * <li>{@code employees://organization} — Org hierarchy</li>
 * <li>{@code employees://managers} — All managers</li>
 * <li>{@code employees://statistics} — Aggregate statistics</li>
 * <li>{@code employees://engineering} — Engineering dept</li>
 * <li>{@code employees://sales} — Sales dept</li>
 * <li>{@code employees://finance} — Finance dept</li>
 * <li>{@code employees://hr} — HR dept</li>
 * <li>{@code employees://marketing} — Marketing dept</li>
 * <li>{@code employees://support} — Support dept</li>
 * <li>{@code employees://india} — India employees</li>
 * <li>{@code employees://usa} — USA employees</li>
 * </ul>
 */

@Configuration
public class EmployeeResourceRegistrar {

	private static final Logger log = LoggerFactory.getLogger(EmployeeResourceRegistrar.class);

	private final EmployeeService employeeService;

	/**
	 * ObjectMapper configured to handle Java 8 date/time types (LocalDate).
	 * Resources serialize their content as JSON text manually.
	 */
	private final ObjectMapper objectMapper;

	public EmployeeResourceRegistrar(EmployeeService employeeService) {
		this.employeeService = employeeService;
		// Configure Jackson to handle LocalDate without timestamps
		this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	/**
	 * Registers all 15 MCP Resource specifications as a single bean.
	 *
	 * <p>
	 * The Spring AI autoconfigure reads this bean via
	 * {@code ObjectProvider<List<...>>} and registers each specification with the
	 * stateless MCP server.
	 *
	 * @return list of resource specifications
	 */
	@Bean
	public List<McpStatelessServerFeatures.SyncResourceSpecification> employeeResources() {
		return List.of(allEmployeesResource(), countriesResource(), citiesResource(), departmentsResource(),
				organizationResource(), managersResource(), statisticsResource(), engineeringResource(),
				salesResource(), financeResource(), hrResource(), marketingResource(), supportResource(),
				indiaResource(), usaResource());
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Resource definitions
	// ─────────────────────────────────────────────────────────────────────────

	private McpStatelessServerFeatures.SyncResourceSpecification allEmployeesResource() {
		var resource = McpSchema.Resource.builder().uri("employees://all").name("All Employees")
				.description("Complete list of all 100 employees in the organization.").mimeType("application/json")
				.build();

		return new McpStatelessServerFeatures.SyncResourceSpecification(resource, (ctx, req) -> {
			log.debug("[RESOURCE] Reading employees://all");
			List<Employee> employees = employeeService.findAll();
			return toResourceResult(req.uri(), employees);
		});
	}

	private McpStatelessServerFeatures.SyncResourceSpecification countriesResource() {
		var resource = McpSchema.Resource.builder().uri("employees://countries").name("Countries")
				.description("All unique countries where the organization has employees.").mimeType("application/json")
				.build();

		return new McpStatelessServerFeatures.SyncResourceSpecification(resource, (ctx, req) -> {
			log.debug("[RESOURCE] Reading employees://countries");
			List<String> countries = employeeService.getCountries();
			return toResourceResult(req.uri(), Map.of("countries", countries, "count", countries.size()));
		});
	}

	private McpStatelessServerFeatures.SyncResourceSpecification citiesResource() {
		var resource = McpSchema.Resource.builder().uri("employees://cities").name("Cities")
				.description("All unique cities where the organization has offices.").mimeType("application/json")
				.build();

		return new McpStatelessServerFeatures.SyncResourceSpecification(resource, (ctx, req) -> {
			log.debug("[RESOURCE] Reading employees://cities");
			List<String> cities = employeeService.getCities();
			return toResourceResult(req.uri(), Map.of("cities", cities, "count", cities.size()));
		});
	}

	private McpStatelessServerFeatures.SyncResourceSpecification departmentsResource() {
		var resource = McpSchema.Resource.builder().uri("employees://departments").name("Departments")
				.description("All unique department names in the organization.").mimeType("application/json").build();

		return new McpStatelessServerFeatures.SyncResourceSpecification(resource, (ctx, req) -> {
			log.debug("[RESOURCE] Reading employees://departments");
			List<String> depts = employeeService.getDepartments();
			return toResourceResult(req.uri(), Map.of("departments", depts, "count", depts.size()));
		});
	}

	private McpStatelessServerFeatures.SyncResourceSpecification organizationResource() {
		var resource = McpSchema.Resource.builder().uri("employees://organization").name("Organization Hierarchy")
				.description("Full organization hierarchy tree from CEO down to individual contributors.")
				.mimeType("application/json").build();

		return new McpStatelessServerFeatures.SyncResourceSpecification(resource, (ctx, req) -> {
			log.debug("[RESOURCE] Reading employees://organization");
			Map<String, Object> hierarchy = employeeService.getOrganizationHierarchy();
			return toResourceResult(req.uri(), hierarchy);
		});
	}

	private McpStatelessServerFeatures.SyncResourceSpecification managersResource() {
		var resource = McpSchema.Resource.builder().uri("employees://managers").name("All Managers")
				.description("All employees who manage at least one direct report (CEO, VPs, Directors, Managers).")
				.mimeType("application/json").build();

		return new McpStatelessServerFeatures.SyncResourceSpecification(resource, (ctx, req) -> {
			log.debug("[RESOURCE] Reading employees://managers");
			List<Employee> managers = employeeService.getManagers();
			return toResourceResult(req.uri(), managers);
		});
	}

	private McpStatelessServerFeatures.SyncResourceSpecification statisticsResource() {
		var resource = McpSchema.Resource.builder().uri("employees://statistics").name("Employee Statistics")
				.description(
						"Aggregate statistics: total headcount, active/inactive split, per-country and per-department counts.")
				.mimeType("application/json").build();

		return new McpStatelessServerFeatures.SyncResourceSpecification(resource, (ctx, req) -> {
			log.debug("[RESOURCE] Reading employees://statistics");

			var count = employeeService.countEmployees();
			var byCountry = employeeService.countEmployeesByCountry();
			var byDept = employeeService.countEmployeesByDepartment();

			Map<String, Object> stats = Map.of("totalEmployees", count.getTotalEmployees(), "activeEmployees",
					count.getActiveEmployees(), "inactiveEmployees", count.getInactiveEmployees(), "byCountry",
					byCountry, "byDepartment", byDept);
			return toResourceResult(req.uri(), stats);
		});
	}

	private McpStatelessServerFeatures.SyncResourceSpecification engineeringResource() {
		return departmentResource("employees://engineering", "Engineering Department",
				"All employees in the Engineering department.", "Engineering");
	}

	private McpStatelessServerFeatures.SyncResourceSpecification salesResource() {
		return departmentResource("employees://sales", "Sales Department", "All employees in the Sales department.",
				"Sales");
	}

	private McpStatelessServerFeatures.SyncResourceSpecification financeResource() {
		return departmentResource("employees://finance", "Finance Department",
				"All employees in the Finance department.", "Finance");
	}

	private McpStatelessServerFeatures.SyncResourceSpecification hrResource() {
		return departmentResource("employees://hr", "HR Department", "All employees in the Human Resources department.",
				"HR");
	}

	private McpStatelessServerFeatures.SyncResourceSpecification marketingResource() {
		return departmentResource("employees://marketing", "Marketing Department",
				"All employees in the Marketing department.", "Marketing");
	}

	private McpStatelessServerFeatures.SyncResourceSpecification supportResource() {
		return departmentResource("employees://support", "Support Department",
				"All employees in the Support department.", "Support");
	}

	private McpStatelessServerFeatures.SyncResourceSpecification indiaResource() {
		return countryResource("employees://india", "India Employees", "All employees based in India.", "India");
	}

	private McpStatelessServerFeatures.SyncResourceSpecification usaResource() {
		return countryResource("employees://usa", "USA Employees", "All employees based in the United States.", "USA");
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Helper builders
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Creates a resource specification that returns employees filtered by
	 * department.
	 */
	private McpStatelessServerFeatures.SyncResourceSpecification departmentResource(String uri, String name,
			String description, String department) {

		var resource = McpSchema.Resource.builder().uri(uri).name(name).description(description)
				.mimeType("application/json").build();

		return new McpStatelessServerFeatures.SyncResourceSpecification(resource, (ctx, req) -> {
			log.debug("[RESOURCE] Reading {} (dept={})", uri, department);
			List<Employee> employees = employeeService.findByDepartment(department);
			return toResourceResult(req.uri(),
					Map.of("department", department, "count", employees.size(), "employees", employees));
		});
	}

	/**
	 * Creates a resource specification that returns employees filtered by country.
	 */
	private McpStatelessServerFeatures.SyncResourceSpecification countryResource(String uri, String name,
			String description, String country) {

		var resource = McpSchema.Resource.builder().uri(uri).name(name).description(description)
				.mimeType("application/json").build();

		return new McpStatelessServerFeatures.SyncResourceSpecification(resource, (ctx, req) -> {
			log.debug("[RESOURCE] Reading {} (country={})", uri, country);
			List<Employee> employees = employeeService.findByCountry(country);
			return toResourceResult(req.uri(),
					Map.of("country", country, "count", employees.size(), "employees", employees));
		});
	}

	/**
	 * Serializes any object to JSON and wraps it in an MCP
	 * {@link McpSchema.ReadResourceResult}.
	 *
	 * <p>
	 * Resources must return text content (unlike tools which return typed DTOs). We
	 * serialize manually here using Jackson, then wrap in
	 * {@link McpSchema.TextResourceContents}.
	 *
	 * @param uri  the resource URI (echoed back in the response)
	 * @param data the object to serialize
	 * @return a ReadResourceResult containing the JSON text
	 */
	private McpSchema.ReadResourceResult toResourceResult(String uri, Object data) {
		try {
			String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
			var contents = new McpSchema.TextResourceContents(uri, "application/json", json);
			return new McpSchema.ReadResourceResult(List.of(contents));
		} catch (JsonProcessingException e) {
			log.error("[RESOURCE] JSON serialization failed for uri={}: {}", uri, e.getMessage());
			var error = new McpSchema.TextResourceContents(uri, "application/json",
					"{\"error\": \"Serialization failed: " + e.getMessage() + "\"}");
			return new McpSchema.ReadResourceResult(List.of(error));
		}
	}
}
