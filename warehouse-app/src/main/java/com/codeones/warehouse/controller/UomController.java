package com.codeones.warehouse.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeones.warehouse.entity.Uom;
import com.codeones.warehouse.exception.UomNotFoundeException;
import com.codeones.warehouse.payloads.Response;
import com.codeones.warehouse.payloads.UomRequest;
import com.codeones.warehouse.service.IUomService;
import com.codeones.warehouse.util.AppUtil;

@RestController
@RequestMapping("/api/v1/uom")
public class UomController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UomController.class);
	
	@Autowired
	private IUomService uomService;
	
	@PostMapping("/create")
	public ResponseEntity<Response> createUom(@RequestBody UomRequest request){
		String message = null;
		LOG.info("ABOUT TO ENTERED INTO UomController::createUom(-) METHOD");
		try {
			message = uomService.createUom(request);
			LOG.debug("Success - {}", message);
		} catch (Exception e) {
			throw e;
		}
		LOG.info("ABOUT TO EXIST FROM UomController::createUom(-) METHOD");
		return new ResponseEntity<>(new Response(message, HttpStatus.CREATED.value(), LocalDateTime.now(), null),HttpStatus.CREATED);
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<Response> getUomById(@PathVariable Long id) {
		Uom uom = null;
		LOG.info("ENTERED INTO UomController::getUomById(-) METHOD");
		try {
			uom = uomService.getUomById(id);
			LOG.debug("Success - {}", uom);
		} catch (UomNotFoundeException unfe) {
			throw unfe;
		}
		LOG.info("ABOUT TO EXIST FROM UomController::getUomById(-) METHOD");
		return ResponseEntity.ok(new Response("UOM FETCH SUCCESSFULLY!", HttpStatus.OK.value(), LocalDateTime.now(), uom));
	}
	
	@GetMapping("/all")
	public ResponseEntity<Response> getAllUom() {
		List<Uom> list = null;
		LOG.info("ENTERED INTO UomController::getAllUom(-) METHOD");
		try {
			list = uomService.getAllUom();
			LOG.debug("Success - {}", list);
		} catch (Exception e) {
			throw e;
		}
		LOG.info("ABOUT TO EXIST FROM UomController::getAllUom(-) METHOD");
		return ResponseEntity.ok(new Response("ALL UOM FETCHED SUCCESSFULLY", HttpStatus.OK.value(), LocalDateTime.now(), list));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteUomById(@PathVariable Long id) {
		LOG.info("ENTERED INTO UomController::deleteUomById(-) METHOD");
		try {
			LOG.debug("DELETE PROCESS START FOR ID : {}", id);
			uomService.deleteUomById(id);
		} catch (UomNotFoundeException unfe) {
			throw unfe;
		}
		LOG.info("ABOUT TO EXIST FROM UomController::deleteUomById(-) METHOD");
		return ResponseEntity.ok(new Response("UOM WITH ID : '"+id+"' DELETED SUCCESSFULLY", HttpStatus.OK.value(), LocalDateTime.now(), null));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Response> updateUomById(@RequestBody UomRequest request, @PathVariable Long id){
		String message = null;
		LOG.info("ENTERED INTO UomController::updateUomById(-) METHOD");
		try {
			LOG.debug("Success - {}", message);
			message = uomService.updateUomById(id, request);
		} catch (UomNotFoundeException unfe) {
			throw unfe;
		}
		LOG.info("ABOUT TO EXIST FROM UomController::updateUomById(-) METHOD");
		return ResponseEntity.ok(new Response(message, HttpStatus.OK.value(), LocalDateTime.now(), null));
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
}
