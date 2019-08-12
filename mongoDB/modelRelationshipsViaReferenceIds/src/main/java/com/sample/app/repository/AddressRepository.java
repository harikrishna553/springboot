package com.sample.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sample.app.entity.Address;

@Repository
public interface AddressRepository extends MongoRepository<Address, String> {

}
