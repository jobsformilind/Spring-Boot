package com.test.spring.boot.restservices.user;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 6863376504945198627L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
