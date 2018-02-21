package com.test.spring.boot.bank.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.spring.boot.bank.model.Request;
import com.test.spring.boot.bank.rest.base.BaseRestController;
import com.test.spring.boot.bank.service.RequestService;

@RestController
@RequestMapping(path = "/api/requests", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
class RequestRestController extends BaseRestController<Request> {

	@Autowired
	public RequestRestController(RequestService requestService) {
		super(requestService);
	}
}
