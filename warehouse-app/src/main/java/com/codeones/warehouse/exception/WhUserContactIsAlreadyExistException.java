package com.codeones.warehouse.exception;

public class WhUserContactIsAlreadyExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public WhUserContactIsAlreadyExistException() {
		super();
	}

	public WhUserContactIsAlreadyExistException(String message) {
		super(message);
	}
}
