package com.test.spring.boot.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.spring.boot.bank.model.Specialty;
import com.test.spring.boot.bank.repository.SpecialtyRepository;
import com.test.spring.boot.bank.service.base.BaseService;

@Service
public class SpecialtyService extends BaseService<Specialty> {
	@Autowired
	public SpecialtyService(SpecialtyRepository repository) {
		super(repository);
	}

}
