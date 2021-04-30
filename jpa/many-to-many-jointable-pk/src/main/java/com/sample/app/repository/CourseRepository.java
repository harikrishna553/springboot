package com.sample.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
