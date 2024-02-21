package com.codeones.warehouse.exception;

public class WhUserTypeEmailAlredyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WhUserTypeEmailAlredyExistException() {
		super();
	}
	
	public WhUserTypeEmailAlredyExistException(String message) {
		super(message);
	}
}
