package com.sample.app.entity;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import org.springframework.data.annotation.Id;

@Document("employees")
public class Employee {

	@Id // db document field: _key
	private String key;

	@ArangoId // db document field: _id
	private String arangoId;

	private Integer id;
	private String firstName;
	private String lastName;
	private Integer age;
	private String aboutMe;

	public Employee(Integer id, String firstName, String lastName, Integer age, String aboutMe) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.aboutMe = aboutMe;
	}

	public Employee() {

	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getArangoId() {
		return arangoId;
	}

	public void setArangoId(String arangoId) {
		this.arangoId = arangoId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	@Override
	public String toString() {
		return "Employee [key=" + key + ", arangoId=" + arangoId + ", id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", age=" + age + ", aboutMe=" + aboutMe + "]";
	}

}
