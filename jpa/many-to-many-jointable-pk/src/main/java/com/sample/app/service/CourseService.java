package com.sample.app.service;

import com.sample.app.dto.CourseRequestDto;
import com.sample.app.dto.CourseResponseDto;

public interface CourseService {
	public CourseResponseDto createCourse(CourseRequestDto courseDto);
}
