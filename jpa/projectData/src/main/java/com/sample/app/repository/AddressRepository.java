package com.sample.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.sample.app.model.Address;

public interface AddressRepository  extends CrudRepository<Address, Integer>{

}
