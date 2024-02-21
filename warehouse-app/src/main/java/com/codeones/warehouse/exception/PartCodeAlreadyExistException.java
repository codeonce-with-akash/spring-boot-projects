package com.codeones.warehouse.exception;

public class PartCodeAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PartCodeAlreadyExistException() {
		super();
	}

	public PartCodeAlreadyExistException(String message) {
		super(message);
	}
}
