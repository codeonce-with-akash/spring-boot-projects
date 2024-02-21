package com.codeones.warehouse.service;

import java.util.List;

import com.codeones.warehouse.entity.WhUserType;
import com.codeones.warehouse.payloads.WhUserTypeRequest;

public interface IWhUserTypeService {
	public String createWhUserType(WhUserTypeRequest whUserTypeRequest);
	public Integer getWhUserEmailCount(String whUserEmail);
	public WhUserType getWhUserTypeById(Long id);
	public List<WhUserType> getAllWhUserType();
	public WhUserType updateWhUserType(Long id, WhUserTypeRequest whUserTypeRequest);
	public void deleteWhUserType(Long id);
}
