package com.codeones.warehouse.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.codeones.warehouse.entity.ShipmentType;
import com.codeones.warehouse.exception.ShipmentTypeCodeAlreadyExistException;
import com.codeones.warehouse.exception.ShipmentTypeNotFoundExcepiton;
import com.codeones.warehouse.payloads.ShipmentTypeRequest;
import com.codeones.warehouse.repository.ShipmentTypeRepository;
import com.codeones.warehouse.service.impl.ShipmentTypeServiceImpl;

@SpringBootTest(classes = {ShipmentTypeServiceImplTest.class})
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShipmentTypeServiceImplTest {
	
	@Mock
	ShipmentTypeRepository shipmentTypeRepository;
	
	@InjectMocks
	ShipmentTypeServiceImpl shipmentTypeService;
	
	@Test
	@Order(1)
	@DisplayName("test, get all shipmenttypes")
	void getAllShipmentTypeTest() {
		when(shipmentTypeRepository.findAll())
		.thenReturn(
				List.of(new ShipmentType(101L,"AIR","JETAIR","YES","A","SAMPLE-A"),
						new ShipmentType(102L,"SHIP","TITANIC","NO","B","SAMPLE-B")
						)
				);
		int actualSize = shipmentTypeService.getShipmentTypes().size();
		assertEquals(2, actualSize, "shipmenttypes size is not match");
	}
	
	@Test
	@Order(2)
	@DisplayName("test, create shipmenttype with unique shipmentype code")
	void createShipmentTypeTest() {
		ShipmentTypeRequest request = ShipmentTypeRequest.builder()
		.shipMode("AIR")
		.shipCode("JETAIR")
		.shipGrade("A")
		.enblShip("YES")
		.shipDesc("SAMPLE").build();
		
		ShipmentType shipmentType = ShipmentType.builder()
		.shipMode(request.getShipMode())
		.shipCode(request.getShipCode())
		.shipGrade(request.getShipGrade())
		.enblShip(request.getEnblShip())
		.shipDesc(request.getShipDesc()).build();
		
		
		ShipmentType dbShipmentType = ShipmentType.builder()
		.id(101L)
		.shipMode("AIR")
		.shipCode("JETAIR")
		.shipGrade("A")
		.enblShip("YES")
		.shipDesc("SAMPLE").build();
		
		
		when(shipmentTypeRepository.save(shipmentType))
		.thenReturn(dbShipmentType);
		
		
		assertEquals("ShipmentType created successfully with Id: 101", shipmentTypeService.createShipmentType(request), 
				"shipmenttype creation failed!");
	}
	
	@Test
	@Order(3)
	@DisplayName("test, create shipmenttype with duplicate shipCode")
	void createShipmentTypeTestWithDuplicateShipCode() {
		ShipmentTypeRequest shipmentTypeRequest = ShipmentTypeRequest.builder()
				.shipMode("AIR")
				.shipCode("JETAIR")
				.shipGrade("A")
				.enblShip("YES")
				.shipDesc("SAMPLE").build();
		
		String shipCode = "JETAIR";
		
		when(shipmentTypeRepository.getShipmentTypeCodeCount(shipCode)).thenReturn(1);
		
		assertThrows(ShipmentTypeCodeAlreadyExistException.class, ()-> shipmentTypeService.createShipmentType(shipmentTypeRequest), "Duplicate shipCode!");
	}
	
	@Test
	@Order(4)
	@DisplayName("test, get shipmenttype by id with happy path")
	void getShipmentTypeByIdTest() {
		
		Long id = 101L;
		Long expectedId = 101L;
		
		Optional<ShipmentType> opt = Optional.of(ShipmentType.builder().id(id).shipMode("AIR").shipCode("JETAIR").enblShip("YES").shipGrade("A").build());
		
		when(shipmentTypeRepository.findById(id)).thenReturn(opt);
		
		assertEquals(expectedId, shipmentTypeService.getShipmentTypeById(id).getId(), "Id must be same!");
		
	}

	
	@Test
	@Order(5)
	@DisplayName("test, get shipmenttype by in-correct id with failuer path")
	void getShipmentTypeByInCorrectIdTest() {
		
		Long id = 101L;
		
		when(shipmentTypeRepository.findById(id)).thenThrow(new ShipmentTypeNotFoundExcepiton());
		
		assertThrows(ShipmentTypeNotFoundExcepiton.class, ()-> shipmentTypeService.getShipmentTypeById(id), "Incorrect Id!");
	}
	
	
	@Test
	@Order(6)
	@DisplayName("test, delete shipmenttype with correct id as happy path")
	void deleteShipmentTypeTest() {
		
		Long id = 101L;
		
		Optional<ShipmentType> opt = Optional.of(ShipmentType.builder()
																.id(id)
																.shipMode("AIR")
																.shipCode("JETAIR")
																.enblShip("YES")
																.shipGrade("A")
																.shipDesc("SAMPLE")
																.build()
												);
		
		when(shipmentTypeRepository.findById(id)).thenReturn(opt);
		
		shipmentTypeService.deleteShipmentType(id);
		
		verify(shipmentTypeRepository, times(1)).delete(opt.get());
		
	}
	
	@Test
	@Order(7)
	@DisplayName("test, delete shipment type with in-correct id as failuer path ")
	void deleteShipmentTypeWithIncorrectIdTest() {
		
		Long id = 101L;

		Optional<ShipmentType> opt = Optional.of(ShipmentType.builder()
				.id(id)
				.shipMode("AIR")
				.shipCode("JETAIR")
				.enblShip("YES")
				.shipGrade("A")
				.shipDesc("SAMPLE")
				.build()
				);
		
		when(shipmentTypeRepository.findById(id)).thenThrow(ShipmentTypeNotFoundExcepiton.class);
		
		assertThrows(ShipmentTypeNotFoundExcepiton.class, () -> shipmentTypeService.deleteShipmentType(id));
		
		verify(shipmentTypeRepository, times(0)).delete(opt.get());
	} 
	
	@Test
	@Order(8)
	@DisplayName("test, update shipmenttype with correct id and unique shipcode as happy path")
	void updateShipmentTypeWithCorrectIdAndUniqueShipCode() {
		Long id = 101L;
		ShipmentTypeRequest shipmentTypeRequest = ShipmentTypeRequest.builder().shipCode("INDIANAIR").enblShip("NO").build();
		
		 Optional<ShipmentType> dbShipmentType = Optional.of(ShipmentType.builder()
		.id(id)
		.shipMode("AIR")
		.shipCode("JETAIR")
		.enblShip("YES")
		.shipGrade("A")
		.shipDesc("SAMPLE")
		.build());
		
		when(shipmentTypeRepository.findById(id)).thenReturn(dbShipmentType);
		
		when(shipmentTypeRepository.getShipmentTypeCodeCountForUpdate(id, shipmentTypeRequest.getShipCode())).thenReturn(0);
		
		String message = shipmentTypeService.updateShipmentType(id, shipmentTypeRequest);
		
		assertEquals("ShipmentType '101' updated successfully!", message);
	}
	
	@Test
	@Order(9)
	@DisplayName("test, update shipmenttype with correct id and duplicate shipcode as failuer path")
	void updateShipmentTypeWithCorrectIdAndDuplicateShipCode() {
		Long id = 101L;
		ShipmentTypeRequest shipmentTypeRequest = ShipmentTypeRequest.builder().shipCode("INDIANAIR").enblShip("NO").build();
		
		 Optional<ShipmentType> dbShipmentType = Optional.of(ShipmentType.builder()
		.id(id)
		.shipMode("AIR")
		.shipCode("JETAIR")
		.enblShip("YES")
		.shipGrade("A")
		.shipDesc("SAMPLE")
		.build());
		
		when(shipmentTypeRepository.findById(id)).thenReturn(dbShipmentType);
		
		when(shipmentTypeRepository.getShipmentTypeCodeCountForUpdate(id, shipmentTypeRequest.getShipCode())).thenReturn(1);
		
		assertThrows(ShipmentTypeCodeAlreadyExistException.class, () -> shipmentTypeService.updateShipmentType(id, shipmentTypeRequest));
	}
	
	
	@Test
	@Order(10)
	@DisplayName("test, update shipmenttype with in-correct id and unique shipcode as failuer path")
	void updateShipmentTypeWithInCorrectIdAndUniqueShipCode() {
		Long id = 101L;
		ShipmentTypeRequest shipmentTypeRequest = ShipmentTypeRequest.builder().shipCode("INDIANAIR").enblShip("NO").build();
		
		when(shipmentTypeRepository.findById(id)).thenThrow(ShipmentTypeNotFoundExcepiton.class);
		
		assertThrows(ShipmentTypeNotFoundExcepiton.class, () -> shipmentTypeService.updateShipmentType(id, shipmentTypeRequest));
	}
}
