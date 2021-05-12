package com.sample.app.entity;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Relations;

@Document("projects")
public class Project {
	@Id // db document field: _key
	private String key;

	@ArangoId // db document field: _id
	private String arangoId;

	private Integer pjtId;

	private String projectName;

	@Relations(edges = WorkingIn.class, lazy = true)
	private Collection<Employee> employees;

	public Project(Integer pjtId, String projectName) {
		super();
		this.pjtId = pjtId;
		this.projectName = projectName;
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

	public Integer getPjtId() {
		return pjtId;
	}

	public void setPjtId(Integer pjtId) {
		this.pjtId = pjtId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Collection<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Collection<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Project [key=" + key + ", arangoId=" + arangoId + ", pjtId=" + pjtId + ", projectName=" + projectName
				+ "]";
	}

}
