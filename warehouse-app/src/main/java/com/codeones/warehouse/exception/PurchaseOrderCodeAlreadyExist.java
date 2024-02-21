package com.codeones.warehouse.exception;

public class PurchaseOrderCodeAlreadyExist extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PurchaseOrderCodeAlreadyExist() {
		super();
	}
	
	public PurchaseOrderCodeAlreadyExist(String message) {
		super(message);
	}

}
