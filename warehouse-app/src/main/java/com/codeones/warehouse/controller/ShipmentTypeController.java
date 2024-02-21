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

import com.codeones.warehouse.entity.ShipmentType;
import com.codeones.warehouse.exception.ShipmentTypeCodeAlreadyExistException;
import com.codeones.warehouse.exception.ShipmentTypeNotFoundExcepiton;
import com.codeones.warehouse.payloads.ShipmentTypeRequest;
import com.codeones.warehouse.payloads.Response;
import com.codeones.warehouse.service.IShipmentTypeService;
import com.codeones.warehouse.util.AppUtil;

@RestController
@RequestMapping("/api/v1/shipmenttype")
public class ShipmentTypeController {

	private static final Logger log = LoggerFactory.getLogger(ShipmentTypeController.class);

	@Autowired
	private IShipmentTypeService shipmentTypeService;

	@PostMapping("/create")
	public ResponseEntity<Response> createShipmentType(@RequestBody ShipmentTypeRequest request) {
		String message = null;
		log.info("ENTERED INTO ShipmentTypeController::createShipmentType(-) METHOD");
		try {
			message = shipmentTypeService.createShipmentType(request);
			log.debug("SUCCESS: {}", message);
		}catch (ShipmentTypeCodeAlreadyExistException stcaee) {
			throw stcaee;
		} 
		catch (Exception e) {
			throw e;
		}
		log.info("ABOUT TO EXIT FROM ShipmentTypeController::createShipmentType(-) METHOD");
		return new ResponseEntity<>(
				new Response(message, HttpStatus.CREATED.value(), LocalDateTime.now(), null),
				HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<Response> getShipmentTypes() {
		List<ShipmentType> shipmentTypes = null;
		log.info("ENTERED INTO ShipmentTypeController::getShipmentTypes(-) METHOD");
		try {
			shipmentTypes = shipmentTypeService.getShipmentTypes();
			log.debug("SUCCESS: {}", shipmentTypes);
		} catch (Exception e) {
			throw e;
		}
		log.info("ABOUT TO EXIT FROM ShipmentTypeController::getShipmentTypes(-) METHOD");
		return ResponseEntity.ok(new Response("ShipmentTypes are fetched successfully!",
				HttpStatus.OK.value(), LocalDateTime.now(), shipmentTypes));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteShipmentType(@PathVariable Long id) {
		log.info("ENTERED INTO ShipmentTypeController::deleteShipmentType(-) METHOD");
		try {
			shipmentTypeService.deleteShipmentType(id);
			log.debug("SHIPMENTTYPE {} DELETED SUCCESSFULLY", id);
		} catch (ShipmentTypeNotFoundExcepiton stnfe) {
			throw stnfe;
		}
		log.info("ABOUT TO EXIT FROM ShipmentTypeController::deleteShipmentType(-) METHOD");
		return ResponseEntity.ok(new Response("ShipmentType '" + id + "' is deleted successfully!",
				HttpStatus.OK.value(), LocalDateTime.now(), null));
	}

	@GetMapping("/one/{id}")
	public ResponseEntity<Response> getShipmentType(@PathVariable(name = "id") Long id) {
		ShipmentType shipmentType = null;
		log.info("ENTERED INTO ShipmentTypeController::getShipmentType(-)");
		try {
			shipmentType = shipmentTypeService.getShipmentTypeById(id);
			log.debug("SUCCESS: ShipmentType is found with id: {}", id);
		} catch (ShipmentTypeNotFoundExcepiton stnfe) {
			throw stnfe;
		}
		log.info("ABOUT TO EXIT FROM ShipmentTypeController::getShipmentType(-) METHOD");
		return ResponseEntity.ok(new Response("ShipmentType with id : '" + id + "' fetched successfully!",
				HttpStatus.OK.value(), LocalDateTime.now(), shipmentType));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Response> updateShipmentType(@PathVariable(name = "id") Long id,
			@RequestBody ShipmentTypeRequest request) {
		String msg = null;
		log.info("ENTERED INTO ShipmentTypeController::updateShipmentType(-,-) METHOD");
		try {
			msg = shipmentTypeService.updateShipmentType(id, request);
			log.debug("SUCCESS: {}", msg);
		}catch (ShipmentTypeCodeAlreadyExistException  stcaee) {
			throw stcaee;
		} 
		catch (ShipmentTypeNotFoundExcepiton stnfe) {
			throw stnfe;
		}
		log.info("ABOUT TO EXIT FROM ShipmentTypeController::updateShipmentType(-,-) METHOD");
		return ResponseEntity.ok(new Response(msg, HttpStatus.OK.value(), LocalDateTime.now(), null));
	}
}