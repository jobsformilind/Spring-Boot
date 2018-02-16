package com.test.spring.boot.bank.service.exception;

public class BankRuntimeException extends RuntimeException implements ExceptionDescriptor {
	private static final long serialVersionUID = -7954462441435937525L;
	private int errorCode;
	private String errorKey;
	private String message;

	public BankRuntimeException(int errorCode, String errorKey, String message) {
		this.errorCode = errorCode;
		this.errorKey = errorKey;
		this.message = message;
	}

	public BankRuntimeException(int errorCode, String errorKey, String message, Exception ex) {
		super(ex);
		this.errorCode = errorCode;
		this.errorKey = errorKey;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
