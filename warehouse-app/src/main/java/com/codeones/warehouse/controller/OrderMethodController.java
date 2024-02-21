package com.codeones.warehouse.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeones.warehouse.entity.OrderMethod;
import com.codeones.warehouse.exception.OrderMethodCodeAlreadyExistException;
import com.codeones.warehouse.exception.OrderMethodNotFoundException;
import com.codeones.warehouse.payloads.OrderMethodRequest;
import com.codeones.warehouse.payloads.Response;
import com.codeones.warehouse.service.IOrderMethodService;
import com.codeones.warehouse.util.AppUtil;

@RestController
@RequestMapping("/api/v1/om")
public class OrderMethodController {
	
	private static final Logger LOG = LoggerFactory.getLogger(OrderMethodController.class);
	
	@Autowired
	private IOrderMethodService orderMethodService;

	@PostMapping("/create")
	public ResponseEntity<Response> createOrderMethod(@RequestBody OrderMethodRequest request) {
		LOG.info("ABOUT TO ENTERED INTO OrderMethodController::createOrderMethod(-) METHOD");
		String message = null;
		try {
			 message = orderMethodService.createOrderMethod(request);
			LOG.debug("Success - {}", message);
		} catch (Exception e) {
			throw e;
		}
		LOG.info("ABOUT TO EXIT FROM OrderMethodController::createOrderMethod(-) METHOD");
		return new ResponseEntity<>(new Response(message,
				HttpStatus.CREATED.value(), LocalDateTime.now(), null), HttpStatus.CREATED);
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<Response> getOrderMethodById(@PathVariable Long id){
		OrderMethod orderMethod = null;
		LOG.info("ABOUT TO ENTERED INTO OrderMethodController::getOrderMethodById(-) METHOD");
		try {
			orderMethod = orderMethodService.getMethodOrderById(id);
			LOG.debug("Success - {}", orderMethod);
		} catch (OrderMethodNotFoundException omnfe) {
			throw omnfe;
		}
		LOG.info("ABOUT TO EXIT FROM OrderMethodController::getOrderMethodById(-) METHOD");
		return new ResponseEntity<>(new Response("Order Method Found", HttpStatus.OK.value(), LocalDateTime.now(), orderMethod), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Response> getAllOrderMethod(){
		LOG.info("ABOUT TO ENTERED INTO OrderMethodController::getAllOrderMethod(-) METHOD");
		List<OrderMethod> list = null;
		try {
			list = orderMethodService.getAllOrderMethods();
			LOG.debug("SUCCESS - {}", list);
		} catch (Exception e) {
			throw e;
		}
		LOG.info("ABOUT TO EXIT FROM OrderMethodController::getAllOrderMethod(-) METHOD");
		return new ResponseEntity<>(new Response("ALL METHOD ORDERS ARE FETCHED SUCCESSFULLY", HttpStatus.OK.value(), LocalDateTime.now(), list), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteOrderMethodById(@PathVariable Long id){
		LOG.info("ABOUT TO ENTERED INTO OrderMethodController::deleteOrderMethodById(-) METHOD");
		try {
			orderMethodService.deleteOrderMethodById(id);
			LOG.debug("Success - OrderMethod Deleted {} Successfully", id);
		} catch (OrderMethodNotFoundException omnfe) {
			throw omnfe;
		}
		LOG.info("ABOUT TO EXIT FROM OrderMethodController::deleteOrderMethodById(-) METHOD");
		return new ResponseEntity<>(new Response("OrderMethod '"+id+"' Deleted Successfully", HttpStatus.OK.value(), LocalDateTime.now(), null), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Response> updateOrderMethod(@PathVariable Long id, @RequestBody OrderMethodRequest orderMethodRequest){
		String message = null;
		LOG.info("ABOUT TO ENTERED INTO OrderMethodController::updateOrderMethod(-) METHOD");
		try {
			 message = orderMethodService.updateMethodOrder(id, orderMethodRequest);
			 LOG.debug("Success - {}", message);
		} catch (OrderMethodNotFoundException omnfe) {
			throw omnfe;
		} catch (OrderMethodCodeAlreadyExistException omcaee) {
			
			throw omcaee;
		}
		LOG.info("ABOUT TO EXIT FROM OrderMethodController::updateOrderMethod(-) METHOD");
		return new ResponseEntity<>(new Response(message, HttpStatus.OK.value(), LocalDateTime.now(), null), HttpStatus.OK);
	}
}
