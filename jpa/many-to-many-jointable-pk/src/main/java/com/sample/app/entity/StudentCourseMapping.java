package com.sample.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "STUDENT_COURSE_MAPPING")
public class StudentCourseMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "STUDENT_ID")
	private Integer studentId;

	@Column(name = "COURSE_ID")
	private Integer courseId;

	// bi-directional many-to-one association to Student
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "STUDENT_ID", insertable = false, updatable = false)
	private Student student;

	// bi-directional many-to-one association to Course
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "COURSE_ID", insertable = false, updatable = false)
	private Course course;

	public StudentCourseMapping() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

}