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
	private String name;
	private double locationLongitude;
	private double locationLatitude;

	public Employee(Integer id, String name, double locationLongitude, double locationLatitude) {
		super();

		this.id = id;
		this.name = name;
		this.locationLongitude = locationLongitude;
		this.locationLatitude = locationLatitude;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLocationLongitude() {
		return locationLongitude;
	}

	public void setLocationLongitude(double locationLongitude) {
		this.locationLongitude = locationLongitude;
	}

	public double getLocationLatitude() {
		return locationLatitude;
	}

	public void setLocationLatitude(double locationLatitude) {
		this.locationLatitude = locationLatitude;
	}

	@Override
	public String toString() {
		return "Employee [key=" + key + ", arangoId=" + arangoId + ", id=" + id + ", name=" + name
				+ ", locationLongitude=" + locationLongitude + ", locationLatitude=" + locationLatitude + "]";
	}

}
