package com.codeones.warehouse.service;

import java.util.List;

import com.codeones.warehouse.entity.ShipmentType;
import com.codeones.warehouse.payloads.ShipmentTypeRequest;

public interface IShipmentTypeService {
	String createShipmentType(ShipmentTypeRequest shipmentTypeRequest);
	List<ShipmentType> getShipmentTypes();
	void deleteShipmentType(Long id);
	ShipmentType getShipmentTypeById(Long id);
	String updateShipmentType(Long id, ShipmentTypeRequest request);
	
	Integer getShipmentTypeCodeCount(String code);
	
	List<Object[]> getShipmentTypeModeAndCount();
}
