package com.sample.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sample.app.entity.StudentCourseMapping;
import org.springframework.data.jpa.repository.Query;

public interface StudentCourseMappingRepository extends CrudRepository<StudentCourseMapping, Integer> {

	@Query(value = "select s.course_id from STUDENT_COURSE_MAPPING s WHERE s.student_id= :studentId", nativeQuery = true)
	List<Integer> findAllCourseIds(@Param("studentId") Integer studentId);
}
