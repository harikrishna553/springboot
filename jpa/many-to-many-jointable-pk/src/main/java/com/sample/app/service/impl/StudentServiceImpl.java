package com.sample.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.app.dto.CourseResponseDto;
import com.sample.app.dto.StudentAndCoursesRespDto;
import com.sample.app.dto.StudentRequestDto;
import com.sample.app.dto.StudentResponseDto;
import com.sample.app.entity.Course;
import com.sample.app.entity.Student;
import com.sample.app.entity.StudentCourseMapping;
import com.sample.app.repository.CourseRepository;
import com.sample.app.repository.StudentCourseMappingRepository;
import com.sample.app.repository.StudentRepository;
import com.sample.app.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private StudentCourseMappingRepository studentCourseMappingRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Override
	@Transactional
	public StudentResponseDto createStudent(StudentRequestDto studentDto) {
		Student student = new Student();
		student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		student = studentRepo.save(student);

		StudentResponseDto dto = new StudentResponseDto();
		dto.setId(student.getId());
		dto.setFirstName(student.getFirstName());
		dto.setLastName(student.getLastName());

		return dto;
	}

	@Override
	@Transactional
	public void addCoursesToStudent(Integer studentId, Set<Integer> courseIds) {
		List<StudentCourseMapping> mappings = new ArrayList<>();

		for (Integer courseId : courseIds) {
			StudentCourseMapping mapping = new StudentCourseMapping();

			mapping.setStudentId(studentId);
			mapping.setCourseId(courseId);

			mappings.add(mapping);
		}

		studentCourseMappingRepository.saveAll(mappings);
	}

	@Override
	public StudentAndCoursesRespDto getStudentDetails(Integer studentId) {
		Student student = studentRepo.findById(studentId).get();
		StudentAndCoursesRespDto respDto = new StudentAndCoursesRespDto();
		respDto.setFirstName(student.getFirstName());
		respDto.setLastName(student.getLastName());
		respDto.setId(student.getId());

		List<Integer> courseIds = studentCourseMappingRepository.findAllCourseIds(studentId);

		if (courseIds == null || courseIds.isEmpty()) {
			return respDto;
		}

		List<Course> courses = courseRepository.findAllById(courseIds);

		List<CourseResponseDto> coursesDto = new ArrayList<>();

		for (Course course : courses) {
			CourseResponseDto courseDto = new CourseResponseDto();
			courseDto.setId(course.getId());
			courseDto.setCourseName(course.getCourseName());

			coursesDto.add(courseDto);
		}

		respDto.setCourses(coursesDto);

		return respDto;
	}

}
