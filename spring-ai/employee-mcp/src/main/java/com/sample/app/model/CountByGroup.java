package com.sample.app.model;

/**
 * Generic key-count pair used by grouping tools.
 *
 * <p>
 * Returned by:
 * <ul>
 * <li>{@code countEmployeesByCountry} — group = country name</li>
 * <li>{@code countEmployeesByDepartment} — group = department name</li>
 * </ul>
 *
 * <p>
 * Example JSON:
 * 
 * <pre>
 * { "group": "India", "count": 28 }
 * </pre>
 */

public class CountByGroup {

	/** The group label (country name, department name, etc.). */
	private String group;

	/** Number of employees in this group. */
	private long count;

	public CountByGroup() {
	}

	public CountByGroup(String group, long count) {
		this.group = group;
		this.count = count;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
