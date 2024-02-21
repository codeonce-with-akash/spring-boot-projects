package com.codeones.warehouse.exception;

public class WhUserIdNumberAlreadyExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public WhUserIdNumberAlreadyExistException() {
		super();
	}

	public WhUserIdNumberAlreadyExistException(String message) {
		super(message);
	}

}
