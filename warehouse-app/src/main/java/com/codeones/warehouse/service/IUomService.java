package com.codeones.warehouse.service;

import java.util.List;

import com.codeones.warehouse.entity.Uom;
import com.codeones.warehouse.payloads.UomRequest;

public interface IUomService {
	public String createUom(UomRequest request);
	public Uom getUomById(Long id);
	public List<Uom> getAllUom();
	public void deleteUomById(Long id);
	public String updateUomById(Long id, UomRequest request);
}
