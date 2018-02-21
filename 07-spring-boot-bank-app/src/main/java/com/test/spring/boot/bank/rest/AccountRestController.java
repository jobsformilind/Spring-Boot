package com.test.spring.boot.bank.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.spring.boot.bank.model.Account;
import com.test.spring.boot.bank.rest.base.BaseRestController;
import com.test.spring.boot.bank.service.AccountService;

@RestController
@RequestMapping(path = "/api/accounts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
class AccountRestController extends BaseRestController<Account> {

	@Autowired
	public AccountRestController(AccountService accountService) {
		super(accountService);
	}
}
