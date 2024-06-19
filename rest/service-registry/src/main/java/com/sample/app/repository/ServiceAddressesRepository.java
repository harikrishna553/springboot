package com.sample.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.app.entity.ServiceAddresses;

@Repository
public interface ServiceAddressesRepository extends JpaRepository<ServiceAddresses, Integer> {
	
}