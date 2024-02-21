package com.codeones.warehouse.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeones.warehouse.entity.PurchaseOrder;
import com.codeones.warehouse.exception.PurchaseOrderCodeAlreadyExist;
import com.codeones.warehouse.exception.PurchaseOrderNotFoundException;
import com.codeones.warehouse.exception.ShipmentTypeNotFoundExcepiton;
import com.codeones.warehouse.exception.WhUserTypeNotFoundException;
import com.codeones.warehouse.payloads.PurchaseOrderRequest;
import com.codeones.warehouse.payloads.Response;
import com.codeones.warehouse.service.IPurchaseOrderService;

@RestController
@RequestMapping("api/v1/po")
public class PurchaseOrderController {

	@Autowired
	private IPurchaseOrderService purchaseOrderService;

	@PostMapping("/create")
	public ResponseEntity<Response> craetePurchaseOrder(@RequestBody PurchaseOrderRequest request) {
		String message = null;
		try {
			message = purchaseOrderService.createPurchaseOrder(request);
		} catch (PurchaseOrderCodeAlreadyExist pocae) {
			throw pocae;
		} catch (ShipmentTypeNotFoundExcepiton stnfe) {
			throw stnfe;
		} catch (WhUserTypeNotFoundException wunfe) {
			throw wunfe;
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(new Response(message, HttpStatus.CREATED.value(), LocalDateTime.now(), null),
				HttpStatus.CREATED);
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<Response> getAllPOs(){
		List<PurchaseOrder> list = null;
		try {
			list = purchaseOrderService.getAllPOs();
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(new Response("Purchase Orders",HttpStatus.OK.value(), LocalDateTime.now(), list),HttpStatus.OK);
	}
	
	@GetMapping("/get/one/{poId}")
	public ResponseEntity<Response> getOnePO(@PathVariable Long poId){
		PurchaseOrder po = null;
		try {
			po = purchaseOrderService.getOnePO(poId);
		}catch (PurchaseOrderNotFoundException ponfe) {
			throw ponfe;
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(new Response("Purchase Order", HttpStatus.OK.value(), LocalDateTime.now(), po), HttpStatus.OK);
	}
	
	@PutMapping("/update/{poId}")
	public ResponseEntity<Response> updatePO(@PathVariable Long poId, @RequestBody PurchaseOrderRequest request){
		PurchaseOrder updatedPurchaseOrder = null;
		try {
			updatedPurchaseOrder = purchaseOrderService.updatePO(poId, request);
		} catch (PurchaseOrderNotFoundException ponfe) {
			throw ponfe;
		} catch (PurchaseOrderCodeAlreadyExist pocae) {
			throw pocae;
		} catch (ShipmentTypeNotFoundExcepiton stnfe) {
			throw stnfe;
		} catch (WhUserTypeNotFoundException wtnfe) {
			throw wtnfe;
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(new Response("Purchase Order Modified!", HttpStatus.OK.value(), LocalDateTime.now(), updatedPurchaseOrder),HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
