package com.sample.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sample.app.model.Address;

@RepositoryRestResource(exported=false)
public interface AddressRepository extends CrudRepository<Address, Integer> {

}
