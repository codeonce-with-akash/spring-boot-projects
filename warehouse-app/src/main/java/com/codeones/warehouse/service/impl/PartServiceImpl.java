package com.codeones.warehouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeones.warehouse.entity.Part;
import com.codeones.warehouse.exception.PartCodeAlreadyExistException;
import com.codeones.warehouse.exception.PartIncorrectValuesException;
import com.codeones.warehouse.exception.PartNotFoundException;
import com.codeones.warehouse.payloads.PartRequest;
import com.codeones.warehouse.repository.PartRepository;
import com.codeones.warehouse.service.IOrderMethodService;
import com.codeones.warehouse.service.IPartService;
import com.codeones.warehouse.service.IUomService;

@Service
public class PartServiceImpl implements IPartService {

	@Autowired
	private PartRepository partRepository;

	@Autowired
	private IUomService uomService;

	@Autowired
	private IOrderMethodService orderMethodService;

	@Override
	public String createPart(PartRequest partRequest) {
		Part part = new Part();
		
		if(partRepository.getPartCodeCount(partRequest.getPartCode())>0)
			throw new PartCodeAlreadyExistException(partRequest.getPartCode()+" already exist!");
		part.setPartCode(partRequest.getPartCode());
		
		if (partRequest.getBaseCost().intValue() <= 0 || partRequest.getWid().intValue() <= 0 || partRequest.getLen().intValue() <= 0 || partRequest.getHt().intValue() <= 0)
			throw new PartIncorrectValuesException(
					"The provided values of basecost, width, length or hight may be less than or equals to zero!");
		part.setWid(partRequest.getWid());
		part.setLen(partRequest.getLen());
		part.setHt(partRequest.getHt());
		part.setBaseCost(partRequest.getBaseCost());
		part.setBaseCurr(partRequest.getBaseCurr());
		part.setUom(uomService.getUomById(partRequest.getUomId()));
		part.setOrderMethod(orderMethodService.getMethodOrderById(partRequest.getOmId()));
		part.setPartDesc(partRequest.getPartDesc());

		Long partId = partRepository.save(part).getId();
		return new StringBuilder("Part ").append(partId).append(" created successfully!").toString();
	}

	@Override
	public List<Part> getAllParts() {
		return partRepository.findAll();
	}
	
	@Override
	public Part getPartById(Long id) {
		return partRepository.findById(id).orElseThrow(() -> new PartNotFoundException("Part '"+id+"' not exist!"));
	}
	
	@Override
	public void deletePartById(Long id) {
		Part part = getPartById(id);
		partRepository.delete(part);
	}
	
	@Override
	public Integer getPartCodeCount(String partCode) {
		return null;
	}
	
	@Override
	public Part updatePart(Long id, PartRequest partRequest) {
		Part dbPart = getPartById(id);
		
		if(partRequest.getPartCode() != null) {
			if(partRepository.getPartCodeCountForUpdate(id, partRequest.getPartCode())>0)
				throw new PartCodeAlreadyExistException(partRequest.getPartCode()+" already exist!");
			else
				dbPart.setPartCode(partRequest.getPartCode());
		}
		
		if (partRequest.getBaseCost().intValue() <= 0 || partRequest.getWid().intValue() <= 0 || partRequest.getLen().intValue() <= 0 || partRequest.getHt().intValue() <= 0)
			throw new PartIncorrectValuesException(
					"The provided values of basecost, width, length or hight may be less than or equals to zero!");	
		if(partRequest.getHt() != null)
			dbPart.setHt(partRequest.getHt());
		if(partRequest.getLen() != null)
			dbPart.setLen(partRequest.getLen());
		if(partRequest.getWid() != null)
			dbPart.setWid(partRequest.getWid());
		if(partRequest.getBaseCost() != null)
			dbPart.setBaseCost(partRequest.getBaseCost());
		
		if(partRequest.getBaseCurr() != null)
			dbPart.setBaseCurr(partRequest.getBaseCurr());
		if(partRequest.getUomId() != null)
			dbPart.setUom(uomService.getUomById(partRequest.getUomId()));
		if(partRequest.getOmId() != null)
			dbPart.setOrderMethod(orderMethodService.getMethodOrderById(partRequest.getOmId()));
		if(partRequest.getPartDesc() != null)
			dbPart.setPartDesc(partRequest.getPartDesc());
		return partRepository.save(dbPart);
	}
}
