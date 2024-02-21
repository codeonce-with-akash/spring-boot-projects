package com.codeones.warehouse.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrderRequest {
	private String poCode;
	private String poRefNum;
	private String poQltyCheck;
	private String poDesc;
	private Long shipmentCode;
	private Long vendor;
}
