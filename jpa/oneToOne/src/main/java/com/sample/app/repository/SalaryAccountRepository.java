package com.sample.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.sample.app.entity.SalaryAccount;

public interface SalaryAccountRepository extends CrudRepository<SalaryAccount, Integer> {

}
