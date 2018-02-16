package com.test.spring.boot.bank.service.exception;

public interface ExceptionDescriptor {

	public int getErrorCode();
	
	public String getErrorKey();

	public String getMessage();
}
