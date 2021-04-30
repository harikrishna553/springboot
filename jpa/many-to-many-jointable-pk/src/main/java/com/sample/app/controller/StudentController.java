package com.sample.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.dto.StudentAndCoursesRespDto;
import com.sample.app.dto.StudentCourseMappingDto;
import com.sample.app.dto.StudentRequestDto;
import com.sample.app.dto.StudentResponseDto;
import com.sample.app.service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Student Controller", value = "This section contains student specific operations")
@RequestMapping(value = "/v1/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@ApiOperation(value = "Add new student", notes = "Add new student")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponseDto> saveStudent(@RequestBody StudentRequestDto studentDto) {

		StudentResponseDto student = studentService.createStudent(studentDto);

		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Add courses to the student", notes = "Add courses to the student")
	@PostMapping(value = "/add-course", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addCoursesToStudent(@RequestBody StudentCourseMappingDto studentCourseMappingDto) {
		studentService.addCoursesToStudent(studentCourseMappingDto.getStudentId(),
				studentCourseMappingDto.getCourseIds());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get Student by Id", notes = "Get student by id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentAndCoursesRespDto> getStudentById(@PathVariable final Integer id) {
		StudentAndCoursesRespDto studentAndCoursesRespDto = studentService.getStudentDetails(id);
		return new ResponseEntity<>(studentAndCoursesRespDto, HttpStatus.OK);
	}
}
