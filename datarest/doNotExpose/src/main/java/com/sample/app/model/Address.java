package com.sample.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "my_address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String street;
	private String city;

	@Column(name = "pin_code")
	private String pinCode;
	private String country;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public static AddressBuilder builder() {
		return new AddressBuilder();
	}

	public static class AddressBuilder {

		private Address address;

		public AddressBuilder() {
			address = new Address();
		}

		public AddressBuilder street(String street) {
			address.setStreet(street);
			return this;
		}

		public AddressBuilder city(String city) {
			address.setCity(city);
			return this;
		}

		public AddressBuilder pinCode(String pinCode) {
			address.setPinCode(pinCode);
			return this;
		}

		public AddressBuilder country(String country) {
			address.setCountry(country);
			return this;
		}

		public Address build() {
			return address;
		}
	}

}
