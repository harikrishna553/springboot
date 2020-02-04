package com.sample.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.sample.app.entity.BankAccount;

public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {

}
