package com.codeones.warehouse.exception;

public class ShipmentTypeNotFoundExcepiton extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ShipmentTypeNotFoundExcepiton() {
		super();
	}
	
	public ShipmentTypeNotFoundExcepiton(String msg) {
		super(msg);
	}

}
