package com.codeones.warehouse.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WhUserTypeRequest {
	private String userType;
	private String userCode;
	private String userEmail;
	private String userContact;
	private String userIdType;
	private String ifOther;
	private String userIdNum;
}
