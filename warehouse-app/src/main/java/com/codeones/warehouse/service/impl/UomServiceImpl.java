package com.codeones.warehouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeones.warehouse.entity.Uom;
import com.codeones.warehouse.exception.UomNotFoundeException;
import com.codeones.warehouse.payloads.UomRequest;
import com.codeones.warehouse.repository.UomRepository;
import com.codeones.warehouse.service.IUomService;

@Service
public class UomServiceImpl implements IUomService {

	@Autowired
	private UomRepository uomRepo;

	@Override
	public String createUom(UomRequest request) {
		Uom uom = Uom.builder()
				.uomType(request.getUomType())
				.uomModel(request.getUomModel())
				.desc(request.getDesc())
				.build();
			Long id = uomRepo.save(uom).getId();
		return new StringBuilder().append("UOM ").append(id).append(" created successfully").toString();
	}
	
	@Override
	public Uom getUomById(Long id) {
		return uomRepo.findById(id).orElseThrow(()-> new UomNotFoundeException("UOM NOT FOUND WITH ID: "+id));
	}
	
	@Override
	public List<Uom> getAllUom() {
		return uomRepo.findAll();
	}
	
	@Override
	public void deleteUomById(Long id) {
		Uom uom = getUomById(id);
		if(uom!=null)
			uomRepo.delete(uom);
	}
	
	@Override
	public String updateUomById(Long id, UomRequest request) {
		Uom uom = getUomById(id);
		if(request.getUomType() != null)
			uom.setUomType(request.getUomType());
		if(request.getUomModel() != null)
			uom.setUomModel(request.getUomModel());
		if(request.getDesc() != null)
			uom.setDesc(request.getDesc());
		uomRepo.save(uom);
		return new StringBuilder("Uom ").append(id).append(" Updated Successfully!").toString();
	}
}
