package com.test.spring.boot.bank.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.spring.boot.bank.model.Customer;
import com.test.spring.boot.bank.rest.base.BaseRestController;
import com.test.spring.boot.bank.service.CustomerService;

@RestController
@RequestMapping(path = "/api/owners", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
class CustomerRestController extends BaseRestController<Customer> {

	@Autowired
	public CustomerRestController(CustomerService customerService) {
		super(customerService);
	}
}
