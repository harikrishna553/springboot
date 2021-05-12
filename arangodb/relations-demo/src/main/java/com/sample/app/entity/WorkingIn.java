package com.sample.app.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

@Edge("workingIn")
public class WorkingIn {

	@Id // db document field: _key
	private String id;

	@ArangoId // db document field: _id
	private String arangoId;

	@From
	private Employee emp;

	@To
	private Project pjt;

	public WorkingIn(Employee emp, Project pjt) {
		this.emp = emp;
		this.pjt = pjt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArangoId() {
		return arangoId;
	}

	public void setArangoId(String arangoId) {
		this.arangoId = arangoId;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Project getPjt() {
		return pjt;
	}

	public void setPjt(Project pjt) {
		this.pjt = pjt;
	}

	@Override
	public String toString() {
		return "WorkingIn [id=" + id + ", arangoId=" + arangoId + ", emp=" + emp + ", pjt=" + pjt + "]";
	}

}
