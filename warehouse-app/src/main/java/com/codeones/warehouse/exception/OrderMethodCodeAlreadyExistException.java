package com.codeones.warehouse.exception;

public class OrderMethodCodeAlreadyExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public OrderMethodCodeAlreadyExistException() {
		super();
	}
	
	public OrderMethodCodeAlreadyExistException(String message) {
		super(message);
	}
	
}
