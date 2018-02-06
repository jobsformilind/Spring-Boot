package com.test.spring.boot.restservices.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	MessageSource messageSource;

	@GetMapping(path = "/hello")
	public String hello() {
		return "Hello Spring";
	}

	@GetMapping(path = "/hello/i18n")
	public String helloi18n(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}

	@GetMapping(path = "/hello/bean")
	public HelloBean helloBean() {
		return new HelloBean("Hello Spring Bean");
	}

	@GetMapping(path = "/hello/{name}")
	public HelloBean helloBeanPath(@PathVariable String name) {
		return new HelloBean("Good Morning, " + name);
	}
}
