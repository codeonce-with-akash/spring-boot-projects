package com.codeones.warehouse.service;

import java.util.List;

import com.codeones.warehouse.entity.PurchaseOrder;
import com.codeones.warehouse.payloads.PurchaseOrderRequest;

public interface IPurchaseOrderService {
	public String createPurchaseOrder(PurchaseOrderRequest purchaseOrderRequest);
	public List<PurchaseOrder> getAllPOs();
	public PurchaseOrder getOnePO(Long poId);
	public PurchaseOrder updatePO(Long poId, PurchaseOrderRequest request);
}
