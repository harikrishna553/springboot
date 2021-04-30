package com.sample.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.app.dto.CourseRequestDto;
import com.sample.app.dto.CourseResponseDto;
import com.sample.app.entity.Course;
import com.sample.app.repository.CourseRepository;
import com.sample.app.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	@Transactional
	public CourseResponseDto createCourse(CourseRequestDto courseDto) {
		Course course = new Course();
		course.setCourseName(courseDto.getCourseName());
		course = courseRepository.save(course);

		CourseResponseDto dto = new CourseResponseDto();
		dto.setCourseName(course.getCourseName());
		dto.setId(course.getId());

		return dto;
	}

}
