package com.test.spring.boot.bank.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.spring.boot.bank.model.Specialty;
import com.test.spring.boot.bank.rest.base.BaseRestController;
import com.test.spring.boot.bank.service.SpecialtyService;

@RestController
@RequestMapping(path = "/api/specialities", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
class SpecialityRestController extends BaseRestController<Specialty> {

	@Autowired
	public SpecialityRestController(SpecialtyService specialtyService) {
		super(specialtyService);
	}
}
