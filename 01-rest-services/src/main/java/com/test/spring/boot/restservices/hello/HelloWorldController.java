package com.test.spring.boot.restservices.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping(path = "/hello")
	public String hello() {
		return "Hello Spring";
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
