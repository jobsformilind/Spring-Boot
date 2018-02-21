package com.test.spring.boot.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.spring.boot.bank.model.Banker;
import com.test.spring.boot.bank.repository.BankerRepository;
import com.test.spring.boot.bank.service.base.BaseService;

@Service
public class BankerService extends BaseService<Banker> {
	@Autowired
	public BankerService(BankerRepository repository) {
		super(repository);
	}

}
