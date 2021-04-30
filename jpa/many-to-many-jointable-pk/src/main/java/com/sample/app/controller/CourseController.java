package com.sample.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.dto.CourseRequestDto;
import com.sample.app.dto.CourseResponseDto;
import com.sample.app.service.CourseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Course Controller", value = "This section contains Course specific operations")
@RequestMapping(value = "/v1/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@ApiOperation(value = "Create new course", notes = "Create new course")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CourseResponseDto> saveCourse(@RequestBody CourseRequestDto courseDto) {

		CourseResponseDto course = courseService.createCourse(courseDto);

		return new ResponseEntity<>(course, HttpStatus.CREATED);
	}

}
