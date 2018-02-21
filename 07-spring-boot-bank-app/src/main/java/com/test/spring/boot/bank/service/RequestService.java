package com.test.spring.boot.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.spring.boot.bank.model.Request;
import com.test.spring.boot.bank.repository.RequestRepository;
import com.test.spring.boot.bank.service.base.BaseService;

@Service
public class RequestService extends BaseService<Request> {
	@Autowired
	public RequestService(RequestRepository repository) {
		super(repository);
	}

}
