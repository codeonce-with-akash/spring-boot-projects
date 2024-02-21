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

import com.codeones.warehouse.entity.Part;
import com.codeones.warehouse.exception.PartCodeAlreadyExistException;
import com.codeones.warehouse.exception.PartIncorrectValuesException;
import com.codeones.warehouse.exception.PartNotFoundException;
import com.codeones.warehouse.payloads.PartRequest;
import com.codeones.warehouse.payloads.Response;
import com.codeones.warehouse.service.IPartService;

@RestController
@RequestMapping("/api/v1/part")
public class PartController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PartController.class);
	
	@Autowired
	private IPartService partService;
	
	@PostMapping("/create")
	public ResponseEntity<Response> createPart(@RequestBody PartRequest partRequest){
		String message = null;
		LOG.info("ABOUT TO ENTERED INTO PartController::createPart(-) METHOD");
		try {
			 message = partService.createPart(partRequest);
			 LOG.debug("Success - {}", message);
		} catch (PartIncorrectValuesException pive) {
			pive.printStackTrace();
			throw pive;
		} catch (PartCodeAlreadyExistException pcaee) {
			pcaee.printStackTrace();
			throw pcaee;
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		LOG.info("ABOUT TO EXIT FROM PartController::createPart(-) METHOD");
		return new ResponseEntity<>(new Response(message, HttpStatus.CREATED.value(), LocalDateTime.now(), null), HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Response> getAllPart(){
		LOG.info("ABOUT TO ENTERED INTO PartController::getAllPart() METHOD");
		List<Part> list = null;
		try {
			 list = partService.getAllParts();
			 LOG.debug("Success - {}", list);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		LOG.info("ABOUT TO EXIT FROM PartController::getAllPart() METHOD");
		return new ResponseEntity<>(new Response("All parts fetched successfully!", HttpStatus.OK.value(), LocalDateTime.now(), list), HttpStatus.OK);
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<Response> getPartById(@PathVariable Long id){
		LOG.info("ABOUT TO ENTERED INTO PartController::getPartById(-) METHOD");
		Part part = null;
		try {
			part = partService.getPartById(id);
			LOG.debug("Success - {}", part);
		} catch (PartNotFoundException pnfe) {
			pnfe.printStackTrace();
			throw pnfe;
		}
		LOG.info("ABOUT TO EXIT FROM PartController::getPartById(-) METHOD");
		return new ResponseEntity<>(new Response("Part Successfully Fetched", HttpStatus.OK.value(), LocalDateTime.now(), part), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deletePartById(@PathVariable Long id) {
		LOG.info("ABOUT TO ENTERED INTO PartController::getPartById(-) METHOD");
		try {
			LOG.debug("Success - Part {} Deleted Successfully!", id);
			partService.deletePartById(id);
		} catch (PartNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		LOG.info("ABOUT TO EXIT FROM PartController::getPartById(-) METHOD");
		return new ResponseEntity<>(new Response("Part '"+id+"' Deleted Successfully!", HttpStatus.OK.value(), LocalDateTime.now(), null), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Response> updatePart(@PathVariable Long id, @RequestBody PartRequest partRequest){
		Part updatedPart = null;
		try {
			updatedPart = partService.updatePart(id, partRequest);
		} catch (PartNotFoundException pnfe) {
			pnfe.printStackTrace();
			throw pnfe;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return new ResponseEntity<>(new Response("Part Updated Successfully!", HttpStatus.OK.value(), LocalDateTime.now(), updatedPart), HttpStatus.OK);
	}
}
