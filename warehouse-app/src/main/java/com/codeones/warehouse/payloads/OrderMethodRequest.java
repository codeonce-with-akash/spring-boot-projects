package com.codeones.warehouse.payloads;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMethodRequest {
	private String orderMode;
	private String orderCode;
	private String orderType;
	private Set<String> orderAccept;
	private String description;
}
