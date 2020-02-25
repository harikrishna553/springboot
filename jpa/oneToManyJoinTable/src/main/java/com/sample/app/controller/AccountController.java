package com.sample.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.entity.BankAccount;
import com.sample.app.service.BankAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "This section contains all Account Speicifc Operations" })
public class AccountController {

	@Autowired
	private BankAccountService salaryAccService;

	@ApiOperation(value = "Get Account Details", notes = "Get Account Details by id")
	@GetMapping(value = "/accounts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BankAccount> getAccount(@PathVariable final Integer id) {

		BankAccount salaryAcc = salaryAccService.getAccount(id);

		return new ResponseEntity<>(salaryAcc, HttpStatus.CREATED);
	}
}
