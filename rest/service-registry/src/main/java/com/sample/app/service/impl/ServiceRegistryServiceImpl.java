package com.sample.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.dto.ServiceAddressResponseDTO;
import com.sample.app.dto.ServiceRegistryRequestDTO;
import com.sample.app.dto.ServiceRegistryResponseDTO;
import com.sample.app.entity.ServiceAddresses;
import com.sample.app.entity.ServiceRegistry;
import com.sample.app.repository.ServiceRegistryRepository;
import com.sample.app.service.ServiceRegistryService;

@Service
public class ServiceRegistryServiceImpl implements ServiceRegistryService {

	@Autowired
	private ServiceRegistryRepository serviceRegistryRepository;

	@Override
	public ServiceRegistryResponseDTO registerService(ServiceRegistryRequestDTO serviceRegistryRequestDTO) {
		// Check if a service with the same serviceName, version, and environment
		// already exists
		Optional<ServiceRegistry> existingServiceOpt = serviceRegistryRepository
				.findByServiceNameAndVersionAndEnvironment(serviceRegistryRequestDTO.getServiceName(),
						serviceRegistryRequestDTO.getVersion(), serviceRegistryRequestDTO.getEnvironment());

		ServiceRegistry serviceRegistry;
		if (existingServiceOpt.isPresent()) {
			serviceRegistry = existingServiceOpt.get();
			// Add new ServiceAddresses if they do not already exist
			List<ServiceAddresses> newAddresses = serviceRegistryRequestDTO.getServiceAddresses().stream().map(dto -> {
				Optional<ServiceAddresses> existingAddressOpt = serviceRegistry.getServiceAddresses().stream()
						.filter(addr -> addr.getServiceURL().equals(dto.getServiceURL())
								&& addr.getServiceHealthURL().equals(dto.getServiceHealthURL()))
						.findFirst();
				if (existingAddressOpt.isPresent()) {
					return null;
				}
				ServiceAddresses address = new ServiceAddresses();
				address.setServiceURL(dto.getServiceURL());
				address.setServiceHealthURL(dto.getServiceHealthURL());
				address.setServiceRegistry(serviceRegistry);
				return address;
			}).filter(addr -> addr != null).collect(Collectors.toList());

			serviceRegistry.getServiceAddresses().addAll(newAddresses);
		} else {
			serviceRegistry = new ServiceRegistry();
			serviceRegistry.setServiceName(serviceRegistryRequestDTO.getServiceName());
			serviceRegistry.setEnvironment(serviceRegistryRequestDTO.getEnvironment());
			serviceRegistry.setVersion(serviceRegistryRequestDTO.getVersion());
			serviceRegistry.setActive(serviceRegistryRequestDTO.getActive());

			List<ServiceAddresses> serviceAddresses = serviceRegistryRequestDTO.getServiceAddresses().stream()
					.map(dto -> {
						ServiceAddresses address = new ServiceAddresses();
						address.setServiceURL(dto.getServiceURL());
						address.setServiceHealthURL(dto.getServiceHealthURL());
						address.setServiceRegistry(serviceRegistry);
						return address;
					}).collect(Collectors.toList());

			serviceRegistry.setServiceAddresses(serviceAddresses);
		}

		ServiceRegistry savedServiceRegistry = serviceRegistryRepository.save(serviceRegistry);

		return mapToServiceRegistryResponseDTO(savedServiceRegistry);
	}

	@Override
	public void deregisterService(Integer serviceId) {
		serviceRegistryRepository.deleteById(serviceId);
	}

	@Override
	public List<ServiceRegistryResponseDTO> getAllServices() {
		return serviceRegistryRepository.findAll().stream().map(this::mapToServiceRegistryResponseDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<ServiceRegistryResponseDTO> getServiceById(Integer serviceId) {
		return serviceRegistryRepository.findById(serviceId).map(this::mapToServiceRegistryResponseDTO);
	}

	@Override
	public Optional<ServiceRegistryResponseDTO> getServiceByDetails(String serviceName, String version,
			String environment) {
		return serviceRegistryRepository.findByServiceNameAndVersionAndEnvironment(serviceName, version, environment)
				.map(this::mapToServiceRegistryResponseDTO);
	}

	private ServiceRegistryResponseDTO mapToServiceRegistryResponseDTO(ServiceRegistry serviceRegistry) {
		ServiceRegistryResponseDTO dto = new ServiceRegistryResponseDTO();
		dto.setServiceId(serviceRegistry.getServiceId());
		dto.setServiceName(serviceRegistry.getServiceName());
		dto.setEnvironment(serviceRegistry.getEnvironment());
		dto.setVersion(serviceRegistry.getVersion());
		dto.setActive(serviceRegistry.getActive());

		List<ServiceAddressResponseDTO> serviceAddresses = serviceRegistry.getServiceAddresses().stream()
				.map(address -> {
					ServiceAddressResponseDTO addressDTO = new ServiceAddressResponseDTO();
					addressDTO.setServiceInstanceId(address.getServiceInstanceId());
					addressDTO.setServiceURL(address.getServiceURL());
					addressDTO.setServiceHealthURL(address.getServiceHealthURL());
					return addressDTO;
				}).collect(Collectors.toList());

		dto.setServiceAddresses(serviceAddresses);

		return dto;
	}
}
