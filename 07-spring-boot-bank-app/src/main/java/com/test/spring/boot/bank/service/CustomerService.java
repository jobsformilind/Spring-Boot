package com.test.spring.boot.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.spring.boot.bank.model.Customer;
import com.test.spring.boot.bank.repository.CustomerRepository;
import com.test.spring.boot.bank.service.base.BaseService;

@Service
public class CustomerService extends BaseService<Customer> {

	@Autowired
	public CustomerService(CustomerRepository repository) {
		super(repository);
	}
}
