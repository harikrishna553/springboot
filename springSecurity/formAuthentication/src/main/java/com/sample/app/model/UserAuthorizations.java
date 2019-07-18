package com.sample.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_authorizations")
public class UserAuthorizations {

	@Id
	@Column(name = "user_auth_group_id")
	@GeneratedValue
	private int id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "auth_group")
	private String authGroup;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAuthGroup() {
		return authGroup;
	}

	public void setAuthGroup(String authGroup) {
		this.authGroup = authGroup;
	}

}
