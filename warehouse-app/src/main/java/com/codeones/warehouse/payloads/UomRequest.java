package com.codeones.warehouse.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UomRequest {
	private String uomType;
	private String uomModel;
	private String desc;
}
