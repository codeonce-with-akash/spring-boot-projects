package com.codeones.warehouse.exception;

public class ShipmentTypeCodeAlreadyExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ShipmentTypeCodeAlreadyExistException() {
		super();
	}
	
	public ShipmentTypeCodeAlreadyExistException(String message) {
		super(message);
	}
	
}
