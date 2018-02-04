package com.test.spring.boot.restservices.hello;

public class HelloBean {
	String message;

	public HelloBean(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
