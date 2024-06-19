package com.sample.app.service;

import java.util.List;
import java.util.Optional;

import com.sample.app.dto.ServiceRegistryRequestDTO;
import com.sample.app.dto.ServiceRegistryResponseDTO;

public interface ServiceRegistryService {

    ServiceRegistryResponseDTO registerService(ServiceRegistryRequestDTO serviceRegistryRequestDTO);

    void deregisterService(Integer serviceId);

    List<ServiceRegistryResponseDTO> getAllServices();

    Optional<ServiceRegistryResponseDTO> getServiceById(Integer serviceId);

    Optional<ServiceRegistryResponseDTO> getServiceByDetails(String serviceName, String version, String environment);
}