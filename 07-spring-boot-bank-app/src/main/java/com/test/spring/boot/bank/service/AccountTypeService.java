package com.test.spring.boot.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.spring.boot.bank.model.AccountType;
import com.test.spring.boot.bank.repository.AccountTypeRepository;
import com.test.spring.boot.bank.service.base.BaseService;

@Service
public class AccountTypeService extends BaseService<AccountType> {
	@Autowired
	public AccountTypeService(AccountTypeRepository repository) {
		super(repository);
	}

}
