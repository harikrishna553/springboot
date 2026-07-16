package com.sample.app.model;

/**
 * Summary count DTO returned by the {@code countEmployees} tool.
 *
 * <p>
 * Demonstrates how to return structured aggregation results from MCP tools
 * instead of raw numbers. AI clients can present these as a formatted summary.
 */
public class EmployeeCount {

	/** Total number of employees (active + inactive). */
	private long totalEmployees;

	/** Number of currently active employees. */
	private long activeEmployees;

	/** Number of inactive employees (on leave or terminated). */
	private long inactiveEmployees;

	public long getTotalEmployees() {
		return totalEmployees;
	}

	public void setTotalEmployees(long totalEmployees) {
		this.totalEmployees = totalEmployees;
	}

	public long getActiveEmployees() {
		return activeEmployees;
	}

	public void setActiveEmployees(long activeEmployees) {
		this.activeEmployees = activeEmployees;
	}

	public long getInactiveEmployees() {
		return inactiveEmployees;
	}

	public void setInactiveEmployees(long inactiveEmployees) {
		this.inactiveEmployees = inactiveEmployees;
	}

}
