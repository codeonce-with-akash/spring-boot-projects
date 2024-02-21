package com.codeones.warehouse.service;

import java.util.List;

import com.codeones.warehouse.entity.Part;
import com.codeones.warehouse.payloads.PartRequest;

public interface IPartService {
	public String createPart(PartRequest partRequest);
	public List<Part> getAllParts();
	public Part getPartById(Long id);
	public void deletePartById(Long id);
	public Integer getPartCodeCount(String partCode);
	public Part updatePart(Long id, PartRequest partRequest);
}
