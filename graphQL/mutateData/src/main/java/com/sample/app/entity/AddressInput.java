package com.sample.app.entity;

public class AddressInput {
	private String city;
	private String state;
	private String country;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddressInput [city=").append(city).append(", state=").append(state).append(", country=")
				.append(country).append("]");
		return builder.toString();
	}

}
