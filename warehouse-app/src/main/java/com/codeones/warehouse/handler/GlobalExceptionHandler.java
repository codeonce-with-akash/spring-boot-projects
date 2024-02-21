package com.codeones.warehouse.handler;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codeones.warehouse.exception.OrderMethodCodeAlreadyExistException;
import com.codeones.warehouse.exception.OrderMethodNotFoundException;
import com.codeones.warehouse.exception.PartCodeAlreadyExistException;
import com.codeones.warehouse.exception.PartIncorrectValuesException;
import com.codeones.warehouse.exception.PartNotFoundException;
import com.codeones.warehouse.exception.PurchaseOrderCodeAlreadyExist;
import com.codeones.warehouse.exception.PurchaseOrderNotFoundException;
import com.codeones.warehouse.exception.ShipmentTypeCodeAlreadyExistException;
import com.codeones.warehouse.exception.ShipmentTypeNotFoundExcepiton;
import com.codeones.warehouse.exception.UomNotFoundeException;
import com.codeones.warehouse.exception.WhUserTypeEmailAlredyExistException;
import com.codeones.warehouse.exception.WhUserTypeNotFoundException;
import com.codeones.warehouse.payloads.Response;
import com.codeones.warehouse.util.AppUtil;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(ShipmentTypeNotFoundExcepiton.class)
	public ResponseEntity<Response> handleShipmentTypeNotFoundEx(ShipmentTypeNotFoundExcepiton stnfe) {
		LOG.error("Exception - {}", AppUtil.getLogSupport(stnfe));
		return new ResponseEntity<>(
				new Response(
						stnfe.getMessage(), 
						HttpStatus.BAD_REQUEST.value(), 
						LocalDateTime.now(), 
						null), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ShipmentTypeCodeAlreadyExistException.class)
	public ResponseEntity<Response> handleShipmentTypeNotFoundEx(ShipmentTypeCodeAlreadyExistException staee) {
		LOG.error("Exception - {}", AppUtil.getLogSupport(staee));
		return new ResponseEntity<>(
				new Response(
						staee.getMessage(), 
						HttpStatus.BAD_REQUEST.value(), 
						LocalDateTime.now(), 
						null), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UomNotFoundeException.class)
	public ResponseEntity<Response> handleShipmentTypeNotFoundEx(UomNotFoundeException unfe) {
		LOG.error("Exception - {}", AppUtil.getLogSupport(unfe));
		return new ResponseEntity<>(
				new Response(
						unfe.getMessage(), 
						HttpStatus.BAD_REQUEST.value(), 
						LocalDateTime.now(), 
						null), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OrderMethodNotFoundException.class)
	public ResponseEntity<Response> handleOrderMethodNotFoundException(OrderMethodNotFoundException omnfe){
		LOG.error("Exception - {}", AppUtil.getLogSupport(omnfe));
		return new ResponseEntity<>(
				new Response(omnfe.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), null),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OrderMethodCodeAlreadyExistException.class)
	public ResponseEntity<Response> handleOrderMethodAlreadyExistException(OrderMethodCodeAlreadyExistException omcaee){
		LOG.error("Exception - {}", AppUtil.getLogSupport(omcaee));
		return new ResponseEntity<>(
				new Response(omcaee.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), null),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(WhUserTypeNotFoundException.class)
	public ResponseEntity<Response> handleWhUserTypeNotFoundException(WhUserTypeNotFoundException wutnfe) {
		LOG.error("Exception - {}", AppUtil.getLogSupport(wutnfe));
		return new ResponseEntity<>(
				new Response(wutnfe.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), null),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(WhUserTypeEmailAlredyExistException.class)
	public ResponseEntity<Response> handleWhUserTypeEmailAlredyExistException(WhUserTypeEmailAlredyExistException wuteaee) {
		LOG.error("Exception - {}", AppUtil.getLogSupport(wuteaee));
		return new ResponseEntity<>(new Response(wuteaee.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), wuteaee), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PartIncorrectValuesException.class)
	public ResponseEntity<Response> handlePartIncorrectValuesException(PartIncorrectValuesException pive) {
		LOG.error("Exception - {}", AppUtil.getLogSupport(pive));
		return new ResponseEntity<>(new Response(pive.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), null), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PartNotFoundException.class)
	public ResponseEntity<Response> handlePartNotFoundException(PartNotFoundException pnfe) {
		LOG.error("Exception - {}", AppUtil.getLogSupport(pnfe));
		return new ResponseEntity<>(new Response(pnfe.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), null), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PartCodeAlreadyExistException.class)
	public ResponseEntity<Response> handlePartCodeAlreadyExistException(PartCodeAlreadyExistException pcaee) {
		LOG.error("Exception - {}", AppUtil.getLogSupport(pcaee));
		return new ResponseEntity<>(new Response(pcaee.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), null), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PurchaseOrderCodeAlreadyExist.class)
	public ResponseEntity<Response> handlePurchaseOrderCodeAlreadyExist(PurchaseOrderCodeAlreadyExist pocae) {
		LOG.error("Exception - {}", AppUtil.getLogSupport(pocae));
		return new ResponseEntity<>(new Response(pocae.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), null), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PurchaseOrderNotFoundException.class)
	public ResponseEntity<Response> handlePurchaseOrderNotFoundException(PurchaseOrderNotFoundException ponfe){
		LOG.error("Exception - {}", AppUtil.getLogSupport(ponfe));
		return new ResponseEntity<>(
				new Response(
						ponfe.getMessage(), 
						HttpStatus.NOT_FOUND.value(), 
						LocalDateTime.now(), 
						null), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleShipmentException(Exception e) {
		LOG.error("Exception - {}", AppUtil.getLogSupport(e));
		return new ResponseEntity<>(
				new Response(
						e.getMessage(), 
						HttpStatus.INTERNAL_SERVER_ERROR.value(), 
						LocalDateTime.now(), 
						null), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
