package com.sample.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProjectId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "PROJECT_NAME")
	private String projectName;

	@Column(name = "ORGANIZATION")
	private String organization;

	public ProjectId() {

	}

	public ProjectId(String projectName, String organization) {
		this.projectName = projectName;
		this.organization = organization;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

}
