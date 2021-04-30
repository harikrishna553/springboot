package com.sample.app.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "COURSE")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "course_name")
	private String courseName;

	// bi-directional many-to-one association to StudentCourseMapping
	@OneToMany(mappedBy = "course")
	@JsonManagedReference
	private List<StudentCourseMapping> studentCourseMappings;

	public Course() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<StudentCourseMapping> getStudentCourseMappings() {
		return this.studentCourseMappings;
	}

	public void setStudentCourseMappings(List<StudentCourseMapping> studentCourseMappings) {
		this.studentCourseMappings = studentCourseMappings;
	}

	/*
	 * public StudentCourseMapping addStudentCourseMapping(StudentCourseMapping
	 * studentCourseMapping) { getStudentCourseMappings().add(studentCourseMapping);
	 * studentCourseMapping.setCourse(this);
	 * 
	 * return studentCourseMapping; }
	 * 
	 * public StudentCourseMapping removeStudentCourseMapping(StudentCourseMapping
	 * studentCourseMapping) {
	 * getStudentCourseMappings().remove(studentCourseMapping);
	 * studentCourseMapping.setCourse(null);
	 * 
	 * return studentCourseMapping; }
	 */

}