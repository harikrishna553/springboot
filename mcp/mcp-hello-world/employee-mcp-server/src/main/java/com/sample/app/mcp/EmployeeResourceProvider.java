package com.sample.app.mcp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Employee MCP Resource Provider
 *
 * Implements MCP resource specifications. Resources represent files, data, or
 * other content that can be referenced by tools and prompts.
 *
 * Each resource includes:
 * - uri: Unique identifier/path for the resource
 * - name: Human-readable name
 * - description: Purpose and contents of the resource
 * - mimeType: Content type (e.g., text/plain, application/json)
 */
@Component
public class EmployeeResourceProvider {

	/**
	 * Represents a single MCP resource
	 */
	public static class Resource {
		private String uri;
		private String name;
		private String description;
		private String mimeType;

		public Resource(String uri, String name, String description, String mimeType) {
			this.uri = uri;
			this.name = name;
			this.description = description;
			this.mimeType = mimeType;
		}

		public String getUri() { return uri; }
		public String getName() { return name; }
		public String getDescription() { return description; }
		public String getMimeType() { return mimeType; }
	}

	/**
	 * Resource content/output
	 */
	public static class ResourceContent {
		private String text;
		private String mimeType;

		public ResourceContent(String text, String mimeType) {
			this.text = text;
			this.mimeType = mimeType;
		}

		public String getText() { return text; }

		public String getMimeType() {
			return mimeType;
		}
		
		
	}

	public static class ResourceTemplate {
	    private String uriTemplate;
	    private String name;
	    private String title;
	    private String description;
	    private String mimeType;
	    private List<Icon> icons;

	    public ResourceTemplate(String uriTemplate, String name, String title,
	                            String description, String mimeType, List<Icon> icons) {
	        this.uriTemplate = uriTemplate;
	        this.name = name;
	        this.title = title;
	        this.description = description;
	        this.mimeType = mimeType;
	        this.icons = icons;
	    }

	    public String getUriTemplate() { return uriTemplate; }
	    public String getName() { return name; }
	    public String getTitle() { return title; }
	    public String getDescription() { return description; }
	    public String getMimeType() { return mimeType; }
	    public List<Icon> getIcons() { return icons; }
	}
	
	public static class Icon {
	    private String src;
	    private String mimeType;
	    private List<String> sizes;

	    public Icon(String src, String mimeType, List<String> sizes) {
	        this.src = src;
	        this.mimeType = mimeType;
	        this.sizes = sizes;
	    }

	    public String getSrc() { return src; }
	    public String getMimeType() { return mimeType; }
	    public List<String> getSizes() { return sizes; }
	}
	
	public List<Resource> resources() {
		List<Resource> resources = new ArrayList<>();

		resources.add(new Resource(
				"resource://employee/org-chart",
				"Company Organization Chart",
				"Complete organizational structure showing departments, teams, and reporting lines",
				"application/json"
		));

		resources.add(new Resource(
				"resource://compensation/salary-bands",
				"Salary Bands Database",
				"Reference salary bands and ranges for all positions across the organization",
				"application/json"
		));

		resources.add(new Resource(
				"resource://policies/employee-handbook",
				"Employee Handbook",
				"Company policies, benefits, code of conduct, and employee rights documentation",
				"text/markdown"
		));

		resources.add(new Resource(
				"resource://hr/performance-review-guidelines",
				"Performance Review Guidelines",
				"Standards and procedures for conducting employee performance evaluations",
				"text/markdown"
		));

		resources.add(new Resource(
				"resource://learning/training-catalog",
				"Training & Development Catalog",
				"Available courses, certifications, and professional development programs",
				"application/json"
		));

		resources.add(new Resource(
				"resource://compensation/benefits-guide",
				"Benefits Guide",
				"Health insurance, retirement plans, wellness programs, and other employee benefits",
				"text/markdown"
		));

		resources.add(new Resource(
				"resource://finance/department-budgets",
				"Department Budget Allocation",
				"Annual budget allocations for each department including hiring and development budget",
				"application/json"
		));

		resources.add(new Resource(
				"resource://hr/skills-matrix",
				"Organizational Skills Matrix",
				"Database of employee skills, certifications, and expertise across the organization",
				"application/json"
		));

		return resources;
	}

	public ResourceContent getResource(String uri) {
		return switch (uri) {
			case "resource://employee/org-chart" -> getOrgChart();
			case "resource://compensation/salary-bands" -> getSalaryBands();
			case "resource://policies/employee-handbook" -> getEmployeeHandbook();
			case "resource://hr/performance-review-guidelines" -> getPerformanceReviewGuidelines();
			case "resource://learning/training-catalog" -> getTrainingCatalog();
			case "resource://compensation/benefits-guide" -> getBenefitsGuide();
			case "resource://finance/department-budgets" -> getDepartmentBudgets();
			case "resource://hr/skills-matrix" -> getSkillsMatrix();
			default -> new ResourceContent("Resource '" + uri + "' not found", "text/plain");
		};
	}

	private ResourceContent getOrgChart() {
		String orgChart = """
				{
				  "company": "ACME Corp",
				  "departments": [
				    {
				      "id": "eng",
				      "name": "Engineering",
				      "head": "Alice Johnson",
				      "teams": [
				        {"name": "Backend", "lead": "Bob Smith", "members": 8},
				        {"name": "Frontend", "lead": "Carol White", "members": 6},
				        {"name": "DevOps", "lead": "David Chen", "members": 4}
				      ]
				    },
				    {
				      "id": "sales",
				      "name": "Sales",
				      "head": "Emma Davis",
				      "regions": ["North America", "Europe", "APAC"]
				    },
				    {
				      "id": "hr",
				      "name": "Human Resources",
				      "head": "Frank Miller",
				      "teams": [
				        {"name": "Recruitment", "lead": "Grace Lee"},
				        {"name": "Compensation", "lead": "Henry Brown"}
				      ]
				    }
				  ]
				}
				""";
		return new ResourceContent(orgChart,"text/plain");
	}

	private ResourceContent getSalaryBands() {
		String salaryBands = """
				{
				  "bands": [
				    {
				      "id": "L1",
				      "title": "Junior Developer",
				      "minSalary": 60000,
				      "midSalary": 75000,
				      "maxSalary": 90000
				    },
				    {
				      "id": "L2",
				      "title": "Software Engineer",
				      "minSalary": 90000,
				      "midSalary": 115000,
				      "maxSalary": 140000
				    },
				    {
				      "id": "L3",
				      "title": "Senior Engineer",
				      "minSalary": 130000,
				      "midSalary": 160000,
				      "maxSalary": 200000
				    },
				    {
				      "id": "M1",
				      "title": "Engineering Manager",
				      "minSalary": 150000,
				      "midSalary": 185000,
				      "maxSalary": 240000
				    }
				  ]
				}
				""";
		return new ResourceContent(salaryBands, "text/plain");
	}

	private ResourceContent getEmployeeHandbook() {
		String handbook = """
				# Employee Handbook

				## Table of Contents
				1. Welcome to ACME Corp
				2. Company Values and Culture
				3. Code of Conduct
				4. Attendance and Leave
				5. Performance Management
				6. Compensation and Benefits
				7. Professional Development
				8. Health and Safety
				9. Confidentiality and IP
				10. Dispute Resolution

				## Key Policies

				### Work Hours
				- Standard work week: 40 hours
				- Flexible work arrangements available
				- Remote work policy: Up to 3 days/week

				### Leave Policy
				- Vacation: 20 days/year
				- Sick leave: 10 days/year
				- Personal days: 5 days/year
				- Parental leave: As per local laws

				### Code of Conduct
				- Treat all colleagues with respect
				- Maintain professional environment
				- Report violations to HR
				- Zero tolerance for harassment or discrimination
				""";
		return new ResourceContent(handbook, "text/plain");
	}

	private ResourceContent getPerformanceReviewGuidelines() {
		String guidelines = """
				# Performance Review Guidelines

				## Review Schedule
				- Annual reviews in December
				- Mid-year check-ins in June
				- Probation reviews at 3 and 6 months

				## Rating Scale
				- 5: Exceeds Expectations
				- 4: Meets Expectations
				- 3: Partially Meets Expectations
				- 2: Below Expectations
				- 1: Significantly Below Expectations

				## Review Components
				1. Goal Achievement (40%)
				2. Competency Assessment (40%)
				3. Feedback and Development (20%)

				## Calibration Process
				- Department calibration sessions
				- Executive review and approval
				- Consistent rating distribution
				""";
		return new ResourceContent(guidelines, "text/plain");
	}

	private ResourceContent getTrainingCatalog() {
		String catalog = """
				{
				  "courses": [
				    {
				      "id": "JAVA-001",
				      "title": "Advanced Java Development",
				      "provider": "Internal",
				      "duration": "40 hours",
				      "level": "Intermediate"
				    },
				    {
				      "id": "LEAD-001",
				      "title": "Leadership Essentials",
				      "provider": "LinkedIn Learning",
				      "duration": "8 hours",
				      "level": "Beginner"
				    },
				    {
				      "id": "AWS-001",
				      "title": "AWS Solutions Architect",
				      "provider": "AWS",
				      "duration": "48 hours",
				      "level": "Advanced"
				    }
				  ]
				}
				""";
		return new ResourceContent(catalog, "text/plain");
	}

	private ResourceContent getBenefitsGuide() {
		String benefits = """
				# Benefits Guide

				## Health Insurance
				- Medical: PPO and HMO options
				- Dental: Full coverage for preventive care
				- Vision: Eye exams and coverage

				## Retirement Plans
				- 401(k) with 4% company match
				- Roth 401(k) option available
				- Financial planning consultation

				## Wellness Programs
				- Gym membership reimbursement
				- Mental health counseling
				- Annual health screening
				- Wellness workshops

				## Additional Benefits
				- Life insurance (2x salary)
				- Disability insurance (60% salary)
				- Tuition reimbursement ($5,000/year)
				- Commuter benefits
				- Employee discounts
				""";
		return new ResourceContent(benefits, "text/plain");
	}

	private ResourceContent getDepartmentBudgets() {
		String budgets = """
				{
				  "fiscalYear": 2024,
				  "departments": [
				    {
				      "name": "Engineering",
				      "headcount": 25,
				      "hiringBudget": 500000,
				      "developmentBudget": 150000,
				      "totalBudget": 3500000
				    },
				    {
				      "name": "Sales",
				      "headcount": 15,
				      "hiringBudget": 200000,
				      "trainingBudget": 100000,
				      "totalBudget": 2500000
				    },
				    {
				      "name": "HR",
				      "headcount": 8,
				      "hiringBudget": 150000,
				      "systemsBudget": 200000,
				      "totalBudget": 1200000
				    }
				  ]
				}
				""";
		return new ResourceContent(budgets, "text/plain");
	}

	private ResourceContent getSkillsMatrix() {
		String skillsMatrix = """
				{
				  "skills": [
				    {
				      "skillId": "java",
				      "name": "Java Development",
				      "employees": [
				        {"name": "Alice Johnson", "level": "Expert"},
				        {"name": "Bob Smith", "level": "Advanced"}
				      ]
				    },
				    {
				      "skillId": "leadership",
				      "name": "Leadership",
				      "employees": [
				        {"name": "Carol White", "level": "Advanced"},
				        {"name": "David Chen", "level": "Intermediate"}
				      ]
				    }
				  ]
				}
				""";
		return new ResourceContent(skillsMatrix, "text/plain");
	}
	
	public List<ResourceTemplate> templates() {
	    List<ResourceTemplate> templates = new ArrayList<>();

	    templates.add(new ResourceTemplate(
	        "resource://employee/{employeeId}",
	        "Employee Profile",
	        "Employee Profile",
	        "Access employee details by employeeId",
	        "application/json",
	        List.of(new Icon(
	            "https://example.com/user-icon.png",
	            "image/png",
	            List.of("48x48")
	        ))
	    ));

	    templates.add(new ResourceTemplate(
	        "resource://department/{deptId}",
	        "Department Info",
	        "Department Info",
	        "Access department details using department id",
	        "application/json",
	        List.of(new Icon(
	            "https://example.com/department-icon.png",
	            "image/png",
	            List.of("48x48")
	        ))
	    ));

	    return templates;
	}

}
