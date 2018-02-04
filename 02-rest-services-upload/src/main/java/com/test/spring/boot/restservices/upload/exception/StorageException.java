package com.test.spring.boot.restservices.upload.exception;

public class StorageException extends RuntimeException {

	private static final long serialVersionUID = 3439891115789301957L;

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}

	public StorageException(String message) {
		super(message);
	}

}
