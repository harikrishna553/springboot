package com.sample.app.dto;

public class ServiceAddressRequestDTO {
	private String serviceURL;
	private String serviceHealthURL;

	public String getServiceURL() {
		return serviceURL;
	}

	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}

	public String getServiceHealthURL() {
		return serviceHealthURL;
	}

	public void setServiceHealthURL(String serviceHealthURL) {
		this.serviceHealthURL = serviceHealthURL;
	}

}