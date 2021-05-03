package com.sample.app.model;

import java.util.List;
import java.util.Map;

public class Person {
	private int id;
	private String name;
	private List<String> hobbies;
	private Map<String, Object> otherProperties;

	public Person(int id, String name, List<String> hobbies, Map<String, Object> otherProperties) {
		super();
		this.id = id;
		this.name = name;
		this.hobbies = hobbies;
		this.otherProperties = otherProperties;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	public Map<String, Object> getOtherProperties() {
		return otherProperties;
	}

	public void setOtherProperties(Map<String, Object> otherProperties) {
		this.otherProperties = otherProperties;
	}

}
