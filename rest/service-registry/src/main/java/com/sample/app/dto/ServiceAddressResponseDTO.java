package com.sample.app.dto;

public class ServiceAddressResponseDTO {
	private Integer serviceInstanceId;
	private String serviceURL;
	private String serviceHealthURL;

	public Integer getServiceInstanceId() {
		return serviceInstanceId;
	}

	public void setServiceInstanceId(Integer serviceInstanceId) {
		this.serviceInstanceId = serviceInstanceId;
	}

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