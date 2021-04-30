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
@Table(name = "STUDENT")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	// bi-directional many-to-one association to StudentCourseMapping
	@OneToMany(mappedBy = "student")
	@JsonManagedReference
	private List<StudentCourseMapping> studentCourseMappings;

	public Student() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<StudentCourseMapping> getStudentCourseMappings() {
		return this.studentCourseMappings;
	}

	public void setStudentCourseMappings(List<StudentCourseMapping> studentCourseMappings) {
		this.studentCourseMappings = studentCourseMappings;
	}

	/*public StudentCourseMapping addStudentCourseMapping(StudentCourseMapping studentCourseMapping) {
		getStudentCourseMappings().add(studentCourseMapping);
		studentCourseMapping.setStudent(this);

		return studentCourseMapping;
	}

	public StudentCourseMapping removeStudentCourseMapping(StudentCourseMapping studentCourseMapping) {
		getStudentCourseMappings().remove(studentCourseMapping);
		studentCourseMapping.setStudent(null);

		return studentCourseMapping;
	}*/

}