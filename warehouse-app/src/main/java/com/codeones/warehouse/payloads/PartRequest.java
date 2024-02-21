package com.codeones.warehouse.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartRequest {
	private String partCode;
	private Double wid;
	private Double len;
	private Double ht;
	private Double baseCost;
	private String baseCurr;
	private Long uomId;
	private Long omId;
	private String partDesc;
}
