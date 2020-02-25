package com.sample.app.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.dto.BankAccountDto;
import com.sample.app.dto.UserDto;
import com.sample.app.entity.BankAccount;
import com.sample.app.entity.User;
import com.sample.app.repository.UserRepository;
import com.sample.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	@Transactional
	public User save(UserDto empDto) {

		String firstName = empDto.getFirstName();

		String lastName = empDto.getLastName();

		User emp = new User();
		emp.setFirstName(firstName);
		emp.setLastName(lastName);

		List<BankAccountDto> bankAccountsDto = empDto.getAccounts();

		Set<BankAccount> accounts = new HashSet<>();

		for (BankAccountDto accDto : bankAccountsDto) {
			BankAccount bankAccount = new BankAccount();
			bankAccount.setAccountNumber(accDto.getAccountNumber());
			bankAccount.setAddress(accDto.getAddress());
			bankAccount.setBranch(accDto.getBranch());
			bankAccount.setIfscCode(accDto.getIfscCode());

			accounts.add(bankAccount);
		}

		emp.setBankAccounts(accounts);

		return userRepo.save(emp);
	}

	@Override
	public User getById(int id) {
		return userRepo.findById(id).get();
	}

}
