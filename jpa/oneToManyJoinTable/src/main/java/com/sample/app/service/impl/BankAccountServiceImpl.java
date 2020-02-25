package com.sample.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.entity.BankAccount;
import com.sample.app.repository.BankAccountRepository;
import com.sample.app.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountRepository salaryAccRepo;

	@Override
	public BankAccount getAccount(int id) {
		return salaryAccRepo.findById(id).get();
	}

}
