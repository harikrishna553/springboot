package com.sample.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.dto.ServiceRegistryRequestDTO;
import com.sample.app.dto.ServiceRegistryResponseDTO;
import com.sample.app.service.ServiceRegistryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/services")
@Tag(name = "Service Registry", description = "API for managing service registrations")
public class ServiceRegistryController {

	@Autowired
	private ServiceRegistryService serviceRegistryService;

	@Operation(summary = "Register a new service")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Service registered successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRegistryResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@PostMapping("/register")
	public ResponseEntity<ServiceRegistryResponseDTO> registerService(
			@RequestBody ServiceRegistryRequestDTO serviceRegistryRequestDTO) {
		ServiceRegistryResponseDTO registeredService = serviceRegistryService
				.registerService(serviceRegistryRequestDTO);
		return ResponseEntity.status(201).body(registeredService);
	}

	@Operation(summary = "Deregister an existing service")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Service deregistered successfully", content = @Content),
			@ApiResponse(responseCode = "404", description = "Service not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@DeleteMapping("/deregister/{serviceId}")
	public ResponseEntity<Void> deregisterService(@PathVariable Integer serviceId) {
		serviceRegistryService.deregisterService(serviceId);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Get all registered services")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Services retrieved successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRegistryResponseDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@GetMapping
	public ResponseEntity<List<ServiceRegistryResponseDTO>> getAllServices() {
		List<ServiceRegistryResponseDTO> services = serviceRegistryService.getAllServices();
		return ResponseEntity.ok(services);
	}

	@Operation(summary = "Get service details by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Service retrieved successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRegistryResponseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Service not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@GetMapping("/{serviceId}")
	public ResponseEntity<ServiceRegistryResponseDTO> getServiceById(@PathVariable Integer serviceId) {
		return serviceRegistryService.getServiceById(serviceId).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@Operation(summary = "Get service details by serviceName, version, and environment")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Service retrieved successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ServiceRegistryResponseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Service not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@GetMapping("/details")
	public ResponseEntity<ServiceRegistryResponseDTO> getServiceByDetails(@RequestParam String serviceName,
			@RequestParam String version, @RequestParam String environment) {
		return serviceRegistryService.getServiceByDetails(serviceName, version, environment).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
