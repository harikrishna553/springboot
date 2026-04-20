package com.sample.app.mcp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * Employee MCP Prompt Provider
 *
 * Implements MCP prompt specifications. Prompts are templates for guiding
 * language model interactions and retrieving structured information.
 *
 * Each prompt includes: - name: Unique identifier for the prompt - description:
 * Human-readable description of the prompt's purpose - arguments: Optional list
 * of arguments the prompt accepts
 */
@Component
public class EmployeePromptProvider {

	/**
	 * Represents a single MCP prompt
	 */
	public static class Prompt {
		private String name;
		private String description;
		private List<PromptArgument> arguments;

		public Prompt(String name, String description, List<PromptArgument> arguments) {
			this.name = name;
			this.description = description;
			this.arguments = arguments != null ? arguments : new ArrayList<>();
		}

		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}

		public List<PromptArgument> getArguments() {
			return arguments;
		}
	}

	/**
	 * Represents a prompt argument
	 */
	public static class PromptArgument {
		private String name;
		private String description;
		private boolean required;

		public PromptArgument(String name, String description, boolean required) {
			this.name = name;
			this.description = description;
			this.required = required;
		}

		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}

		public boolean isRequired() {
			return required;
		}
	}

	/**
	 * Prompt content/output
	 */
	public static class PromptContent {
		private String text;

		public PromptContent(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

	public List<Prompt> prompts() {
		List<Prompt> prompts = new ArrayList<>();

		// Prompt 1: Employee Profile Summary
		prompts.add(new Prompt("employee-profile-summary",
				"Generate a comprehensive summary of an employee's profile including name, department, role, and key achievements",
				List.of(new PromptArgument("employeeId", "The unique identifier of the employee", true))));

		// Prompt 2: Team Performance Analysis
		prompts.add(new Prompt("team-performance-analysis",
				"Analyze team performance metrics and generate insights about productivity, collaboration, and areas for improvement",
				List.of(new PromptArgument("departmentId", "The unique identifier of the department", true),
						new PromptArgument("period", "The analysis period (e.g., 'Q1', 'monthly')", false))));

		// Prompt 3: Salary Review Recommendation
		prompts.add(new Prompt("salary-review-recommendation",
				"Generate salary review recommendations based on employee performance, market data, and company guidelines",
				List.of(new PromptArgument("employeeId", "The unique identifier of the employee", true),
						new PromptArgument("fiscalYear", "The fiscal year for the review", true))));

		// Prompt 4: Onboarding Checklist
		prompts.add(new Prompt("onboarding-checklist",
				"Generate a customized onboarding checklist for a new employee based on their role and department",
				List.of(new PromptArgument("role", "The position/role of the new employee", true),
						new PromptArgument("department", "The department the employee will join", true))));

		return prompts;
	}

	/**
	 * MCP-compliant response classes
	 */
	public static class McpResponse {
		private String description;
		private List<Message> messages;

		public McpResponse(String description, List<Message> messages) {
			this.description = description;
			this.messages = messages;
		}

		public String getDescription() {
			return description;
		}

		public List<Message> getMessages() {
			return messages;
		}
	}

	public static class Message {
		private String role;
		private Content content;

		public Message(String role, Content content) {
			this.role = role;
			this.content = content;
		}

		public String getRole() {
			return role;
		}

		public Content getContent() {
			return content;
		}
	}

	public static class Content {
		private String type;
		private String text;

		public Content(String type, String text) {
			this.type = type;
			this.text = text;
		}

		public String getType() {
			return type;
		}

		public String getText() {
			return text;
		}
	}

	/**
	 * MCP-compliant getPrompt method
	 */
	public McpResponse getMcpPrompt(String name, Map<String, String> arguments) {
		PromptContent promptContent = switch (name) {
		case "employee-profile-summary" -> handleEmployeeProfileSummary(arguments);
		case "team-performance-analysis" -> handleTeamPerformanceAnalysis(arguments);
		case "salary-review-recommendation" -> handleSalaryReviewRecommendation(arguments);
		case "onboarding-checklist" -> handleOnboardingChecklist(arguments);
		default -> new PromptContent("Prompt '" + name + "' not found");
		};

		// Find the prompt description from the list
		String description = prompts().stream().filter(p -> p.getName().equals(name)).map(Prompt::getDescription)
				.findFirst().orElse("No description available");

		// Wrap in MCP-compliant message
		Message message = new Message("user", new Content("text", promptContent.getText()));

		return new McpResponse(description, List.of(message));
	}

	private PromptContent handleEmployeeProfileSummary(Map<String, String> arguments) {
		String employeeId = arguments.get("employeeId");
		String prompt = String.format("""
				Please provide a comprehensive summary of employee %s including:
				- Full name and contact information
				- Current role and department
				- Employment start date and tenure
				- Key responsibilities
				- Notable achievements and projects
				- Career development goals
				""", employeeId);

		return new PromptContent(prompt);
	}

	private PromptContent handleTeamPerformanceAnalysis(Map<String, String> arguments) {
		String departmentId = arguments.get("departmentId");
		String period = arguments.getOrDefault("period", "current quarter");
		String prompt = String.format("""
				Analyze the performance of department %s for the %s:
				- Overall productivity metrics
				- Key accomplishments and milestones
				- Team collaboration effectiveness
				- Individual contributor highlights
				- Areas needing improvement
				- Recommendations for optimization
				""", departmentId, period);

		return new PromptContent(prompt);
	}

	private PromptContent handleSalaryReviewRecommendation(Map<String, String> arguments) {
		String employeeId = arguments.get("employeeId");
		String fiscalYear = arguments.get("fiscalYear");
		String prompt = String.format("""
				Generate a salary review recommendation for employee %s for fiscal year %s:
				- Current salary and market comparison
				- Performance evaluation summary
				- Contribution impact assessment
				- Recommended adjustment percentage and rationale
				- Proposed effective date
				- Retention risk assessment
				""", employeeId, fiscalYear);

		return new PromptContent(prompt);
	}

	private PromptContent handleOnboardingChecklist(Map<String, String> arguments) {
		String role = arguments.get("role");
		String department = arguments.get("department");
		String prompt = String.format("""
				Generate a detailed onboarding checklist for a new %s in the %s department:
				- Pre-arrival preparation tasks
				- Day 1 orientation activities
				- Week 1 key learnings and meetings
				- First 30 days objectives
				- Training requirements by role
				- Tools and systems access procedures
				- Mentorship and pairing opportunities
				""", role, department);

		return new PromptContent(prompt);
	}

}
