package com.test.spring.boot.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.spring.boot.bank.model.Account;
import com.test.spring.boot.bank.repository.AccountRepository;
import com.test.spring.boot.bank.service.base.BaseService;

@Service
public class AccountService extends BaseService<Account> {

	@Autowired
	public AccountService(AccountRepository repository) {
		super(repository);
	}

}
