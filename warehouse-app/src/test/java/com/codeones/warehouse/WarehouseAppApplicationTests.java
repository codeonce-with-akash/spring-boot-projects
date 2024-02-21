package com.codeones.warehouse;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codeones.warehouse.controller.ShipmentTypeController;

@SpringBootTest()
class WarehouseAppApplicationTests {
	
	@Autowired
	ShipmentTypeController shipmentController;

	@Test
	void contextLoads() {
		Assertions.assertThat(shipmentController).isNotNull();
	}

}
