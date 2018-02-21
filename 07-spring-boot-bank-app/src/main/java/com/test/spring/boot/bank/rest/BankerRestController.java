package com.test.spring.boot.bank.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.spring.boot.bank.model.Banker;
import com.test.spring.boot.bank.rest.base.BaseRestController;
import com.test.spring.boot.bank.service.BankerService;

@RestController
@RequestMapping(path = "/api/bankers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
class BankerRestController extends BaseRestController<Banker> {

	@Autowired
	public BankerRestController(BankerService bankerService) {
		super(bankerService);
	}
}
