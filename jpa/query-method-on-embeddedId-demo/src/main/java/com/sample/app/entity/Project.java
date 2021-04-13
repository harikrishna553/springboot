package com.sample.app.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
public class Project {

	@EmbeddedId
	private ProjectId projectId;

	@Column(name = "STARTED_TIME")
	private Timestamp startedTime;

	@Column(name = "ACTIVE")
	private String active;

	@Column(name = "DESCRIPTION")
	private String description;

	public Project() {

	}

	public Project(String projectName, String organization, Timestamp startedTime, String active, String description) {
		ProjectId projectId = new ProjectId(projectName, organization);
		this.projectId = projectId;
		this.startedTime = startedTime;
		this.active = active;
		this.description = description;
	}

	public ProjectId getProjectId() {
		return projectId;
	}

	public void setProjectId(ProjectId projectId) {
		this.projectId = projectId;
	}

	public Timestamp getStartedTime() {
		return startedTime;
	}

	public void setStartedTime(Timestamp startedTime) {
		this.startedTime = startedTime;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Project [projectId=");
		builder.append(projectId.toString());
		builder.append(", startedTime=");
		builder.append(startedTime);
		builder.append(", active=");
		builder.append(active);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}