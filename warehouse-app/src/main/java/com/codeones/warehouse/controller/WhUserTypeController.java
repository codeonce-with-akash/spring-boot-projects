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

import com.codeones.warehouse.entity.WhUserType;
import com.codeones.warehouse.exception.WhUserTypeEmailAlredyExistException;
import com.codeones.warehouse.exception.WhUserTypeNotFoundException;
import com.codeones.warehouse.payloads.Response;
import com.codeones.warehouse.payloads.WhUserTypeRequest;
import com.codeones.warehouse.service.IWhUserTypeService;

@RestController
@RequestMapping("/api/v1/wh/user/type")
public class WhUserTypeController {

	private static final Logger LOG = LoggerFactory.getLogger(WhUserTypeController.class);
	
	@Autowired
	private IWhUserTypeService whUserTypeService;

	@PostMapping("/create")
	public ResponseEntity<Response> createWhUserType(@RequestBody WhUserTypeRequest whUserTypeRequest) {
		String message = null;
		LOG.info("ABOUT TO ENTERED INTO WhUserTypeController::createWhUserType(-) METHOD");
		try {
			message = whUserTypeService.createWhUserType(whUserTypeRequest);
			LOG.debug("Success - {}", message);
		} catch (WhUserTypeEmailAlredyExistException wuteaee) {
			throw wuteaee;
		} catch (Exception e) {
			throw e;
		}
		LOG.info("ABOUT TO EXIT FROM WhUserTypeController::createWhUserType(-) METHOD");
		return new ResponseEntity<>(new Response(message, HttpStatus.CREATED.value(), LocalDateTime.now(), null), HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getWhUserTypeById(@PathVariable Long id) {
		WhUserType whUserType = null;
		LOG.info("ABOUT TO ENTERED INTO WhUserTypeController::getWhUserTypeById(-)");
		try {
			whUserType = whUserTypeService.getWhUserTypeById(id);
			LOG.debug("Success - {}", whUserType);
		} catch (WhUserTypeNotFoundException wutnfe) {
			throw wutnfe;
		}
		LOG.info("ABOUT TO EXIT FROM WhUserTypeController::getWhUserTypeById(-) METHOD");
		return new ResponseEntity<>(new Response("WhUserType Fetched Successfully!", HttpStatus.OK.value(), LocalDateTime.now(), whUserType), HttpStatus.OK);
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<Response> getAllWhUserType(){
		List<WhUserType> list = null;
		LOG.info("ABOUT TO ENTERED INTO WhUserTypeController::getAllWhUserType() METHOD");
		try {
			list = whUserTypeService.getAllWhUserType();
			LOG.debug("Success - {}", list);
		} catch (Exception e) {
			throw e;
		}
		LOG.info("ABOUT TO EXIT FROM WhUserTypeController::getAllWhUserType(-) METHOD");
		return new ResponseEntity<>(new Response("All WhUserType Fetched Successfully!", HttpStatus.OK.value(), LocalDateTime.now(), list),HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Response> updateWhUserType(@PathVariable Long id, @RequestBody WhUserTypeRequest request) {
		WhUserType updatedWhUserType = null;
		LOG.info("ABOUT TO ENTERED INTO WhUserTypeController::updateWhUserType(-,-) METHOD");
		try {
			updatedWhUserType = whUserTypeService.updateWhUserType(id, request);
			LOG.debug("Success - {}", updatedWhUserType);
		} catch (WhUserTypeEmailAlredyExistException wuteaee) {
			throw wuteaee;
		}
		LOG.info("ABOUT TO EXIT FROM WhUserTypeController::updateWhUserType(-) METHOD");
		return new ResponseEntity<>(new Response("WhUserType Updated Successfully!", HttpStatus.OK.value(), LocalDateTime.now(), updatedWhUserType), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteWhUserTypeById(@PathVariable Long id) {
		LOG.info("ABOUT TO ENTERED INTO WhUserTypeController::deleteWhUserTypeById(-) METHOD");
		try {
			whUserTypeService.deleteWhUserType(id);
			LOG.debug("Success - WhUserType '{}' Deleted Successfully!", id);
		} catch (WhUserTypeNotFoundException wunfe) {
			throw wunfe;
		}
		LOG.info("ABOUT TO EXIT FROM WhUserTypeController::deleteWhUserTypeById(-) METHOD");
		return new ResponseEntity<>(new Response("WhUserType Deleted Successfully!", HttpStatus.OK.value(), LocalDateTime.now(), null), HttpStatus.OK);
	}

}
