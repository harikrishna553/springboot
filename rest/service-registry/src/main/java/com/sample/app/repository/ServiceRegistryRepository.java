package com.sample.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.app.entity.ServiceRegistry;

@Repository
public interface ServiceRegistryRepository extends JpaRepository<ServiceRegistry, Integer> {
	Optional<ServiceRegistry> findByServiceNameAndVersionAndEnvironment(String serviceName, String version, String environment);
}