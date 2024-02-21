package com.codeones.warehouse.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeones.warehouse.entity.ShipmentType;
import com.codeones.warehouse.exception.ShipmentTypeCodeAlreadyExistException;
import com.codeones.warehouse.exception.ShipmentTypeNotFoundExcepiton;
import com.codeones.warehouse.payloads.ShipmentTypeRequest;
import com.codeones.warehouse.repository.ShipmentTypeRepository;
import com.codeones.warehouse.service.IShipmentTypeService;

@Service
public class ShipmentTypeServiceImpl implements IShipmentTypeService {

	@Autowired
	private ShipmentTypeRepository shipmentTypeRepo;

	@Override
	public String createShipmentType(ShipmentTypeRequest shipmentTypeRequest) {
		ShipmentType shipmentType = ShipmentType.builder()
				.shipMode(shipmentTypeRequest.getShipMode())
				.shipCode(shipmentTypeRequest.getShipCode())
				.enblShip(shipmentTypeRequest.getEnblShip())
				.shipGrade(shipmentTypeRequest.getShipGrade())
				.shipDesc(shipmentTypeRequest.getShipDesc())
				.build();
		if (getShipmentTypeCodeCount(shipmentTypeRequest.getShipCode()) > 0)
			throw new ShipmentTypeCodeAlreadyExistException("ShipmentCode '" + shipmentTypeRequest.getShipCode() + "' Is Already Exist!");
		ShipmentType dbShipmentType = shipmentTypeRepo.save(shipmentType);
		return new StringBuilder("ShipmentType created successfully with Id: ").append(dbShipmentType.getId())
				.toString();
	}

	@Override
	public List<ShipmentType> getShipmentTypes() {
		return shipmentTypeRepo.findAll();
	}


	@Override
	public ShipmentType getShipmentTypeById(Long id) {
		/*
		 * Optional<ShipmentType> stOpt = shipmentTypeRepo.findById(id); if
		 * (stOpt.isPresent()) return stOpt.get();
		 */

		return shipmentTypeRepo.findById(id)
				.orElseThrow(() -> new ShipmentTypeNotFoundExcepiton("ShipmentType is not exist for id '" + id + "'"));
	}

	@Override
	public void deleteShipmentType(Long id) {
		ShipmentType shipmentType = getShipmentTypeById(id);
		shipmentTypeRepo.delete(shipmentType);
	}

	@Override
	@Transactional
	public String updateShipmentType(Long id, ShipmentTypeRequest request) {
		ShipmentType dbShipmentType = getShipmentTypeById(id);
		if (dbShipmentType != null) {
			if (request.getShipCode() != null) {
				if(shipmentTypeRepo.getShipmentTypeCodeCountForUpdate(id, request.getShipCode())>0) {
					throw new ShipmentTypeCodeAlreadyExistException("ShipmentTypd Code '"+request.getShipCode()+"' Already exist!");
				}
				dbShipmentType.setShipCode(request.getShipCode());
			}
			if (request.getShipMode() != null)
				dbShipmentType.setShipMode(request.getShipMode());
			if (request.getShipGrade() != null)
				dbShipmentType.setShipGrade(request.getShipGrade());
			if (request.getEnblShip() != null)
				dbShipmentType.setEnblShip(request.getEnblShip());
			if (request.getShipDesc() != null)
				dbShipmentType.setShipDesc(request.getShipDesc());
			shipmentTypeRepo.save(dbShipmentType);
		}
		return new StringBuilder("ShipmentType '" + id + "' updated successfully!").toString();
	}

	@Override
	public Integer getShipmentTypeCodeCount(String code) {
		return shipmentTypeRepo.getShipmentTypeCodeCount(code);
	}
	
	@Override
	public List<Object[]> getShipmentTypeModeAndCount() {
		return shipmentTypeRepo.getShipmentTypeModeAndCount();
	}

}
