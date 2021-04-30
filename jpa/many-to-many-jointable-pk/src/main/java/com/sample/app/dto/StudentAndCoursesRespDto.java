package com.sample.app.dto;

import java.util.List;

public class StudentAndCoursesRespDto extends StudentResponseDto {
	private List<CourseResponseDto> courses;

	public List<CourseResponseDto> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseResponseDto> courses) {
		this.courses = courses;
	}

}
