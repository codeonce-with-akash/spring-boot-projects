package com.codeones.warehouse.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentTypeRequest {
	private String shipMode;
	private String shipCode;
	private String enblShip;
	private String shipGrade;
	private String shipDesc;
}
