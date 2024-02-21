package com.codeones.warehouse.exception;

public class PurchaseOrderNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public PurchaseOrderNotFoundException() {
		super();
	}

	public PurchaseOrderNotFoundException(String message) {
		super(message);
	}
}
