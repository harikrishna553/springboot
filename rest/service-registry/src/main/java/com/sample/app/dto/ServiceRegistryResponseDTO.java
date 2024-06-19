package com.sample.app.dto;

import java.util.List;

public class ServiceRegistryResponseDTO {
	private Integer serviceId;
	private String serviceName;
	private String environment;
	private String version;
	private Boolean active;
	private List<ServiceAddressResponseDTO> serviceAddresses;

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<ServiceAddressResponseDTO> getServiceAddresses() {
		return serviceAddresses;
	}

	public void setServiceAddresses(List<ServiceAddressResponseDTO> serviceAddresses) {
		this.serviceAddresses = serviceAddresses;
	}

}