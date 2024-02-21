package com.codeones.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codeones.warehouse.entity.ShipmentType;
import com.codeones.warehouse.payloads.ShipmentTypeRequest;

public interface ShipmentTypeRepository extends JpaRepository<ShipmentType, Long> {
	
	@Query("SELECT count(shipCode) from ShipmentType where shipCode=:code")
	Integer getShipmentTypeCodeCount(String code);
	
	@Query("SELECT count(shipCode) from ShipmentType where shipCode=:code and id!=:id")
	Integer getShipmentTypeCodeCountForUpdate(Long id, String code);
	
	@Query("SELECT shipMode, COUNT(shipMode) FROM  ShipmentType GROUP BY shipMode")
	List<Object[]> getShipmentTypeModeAndCount();
}
