package com.sample.app.service;

import java.util.Set;

import com.sample.app.dto.StudentAndCoursesRespDto;
import com.sample.app.dto.StudentRequestDto;
import com.sample.app.dto.StudentResponseDto;

public interface StudentService {
	
	public StudentResponseDto createStudent(StudentRequestDto studentDto);
	
	public void addCoursesToStudent(Integer studentId, Set<Integer> courseIds);

	public StudentAndCoursesRespDto getStudentDetails(Integer studentId);
}
