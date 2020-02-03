package com.sample.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.entity.SalaryAccount;
import com.sample.app.repository.SalaryAccountRepository;
import com.sample.app.service.SalaryAccountService;

@Service
public class SalaryAccountServiceImpl implements SalaryAccountService {

	@Autowired
	private SalaryAccountRepository salaryAccRepo;

	@Override
	public SalaryAccount getAccount(int id) {
		return salaryAccRepo.findById(id).get();
	}

}
