package com.test.spring.boot.bank.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.spring.boot.bank.model.AccountType;
import com.test.spring.boot.bank.rest.base.BaseRestController;
import com.test.spring.boot.bank.service.AccountTypeService;

@RestController
@RequestMapping(path = "/api/account-types", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
class AccountTypeRestController extends BaseRestController<AccountType> {

	@Autowired
	public AccountTypeRestController(AccountTypeService accountTypeService) {
		super(accountTypeService);
	}
}
