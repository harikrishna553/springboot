package com.sample.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.sample.app.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

  Person findByName(String name);
}