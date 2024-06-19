package com.sample.app.dto;

import java.util.List;

public class ServiceRegistryRequestDTO {
	private String serviceName;
	private String environment;
	private String version;
	private Boolean active;
	private List<ServiceAddressRequestDTO> serviceAddresses;

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

	public List<ServiceAddressRequestDTO> getServiceAddresses() {
		return serviceAddresses;
	}

	public void setServiceAddresses(List<ServiceAddressRequestDTO> serviceAddresses) {
		this.serviceAddresses = serviceAddresses;
	}

}
