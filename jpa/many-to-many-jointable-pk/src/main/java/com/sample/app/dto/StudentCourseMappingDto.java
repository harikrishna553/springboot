package com.sample.app.dto;

import java.util.Set;

public class StudentCourseMappingDto {
	private Integer studentId;
	private Set<Integer> courseIds;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Set<Integer> getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(Set<Integer> courseIds) {
		this.courseIds = courseIds;
	}

}
