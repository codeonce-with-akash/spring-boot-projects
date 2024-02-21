package com.codeones.warehouse.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.codeones.warehouse.entity.Document;
import com.codeones.warehouse.exception.DocumentNotFoundException;
import com.codeones.warehouse.repository.DocumentRepository;
import com.codeones.warehouse.service.IDocumentService;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class DocumentServiceImpl implements IDocumentService {

	@Autowired
	private DocumentRepository docRepository;
	
	@Override
	public void saveDocument(Document document) {
		docRepository.save(document);
	}
	
	@Override
	public List<Object[]> getDocIdAndName() {
		return docRepository.getDocumentIdAndData();
	}
	
	@Override
	public Document getDocumentById(Long id) {
		return docRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException("Document id '"+id+"' not found!"));
	}
	
	@Override
	public void deleteDocument(Long id) {
		Document document = getDocumentById(id);
		if(document != null)
			docRepository.delete(document);
	}
	
	@Override
	public void downloadDocument(Long id, HttpServletResponse response) throws IOException {
		//1. GET DOCUMENT OBJECT BY ID
		Document document = getDocumentById(id);
		
		//2. PROVIDE FILENAME USING HEADER
		response.setHeader("Content-Disposition","attachment;filename="+document.getDocName());
		
		//3. COPY DATA FROM DOCUMENT TO RESPONSE
		FileCopyUtils.copy(document.getDocData(), response.getOutputStream());
	}

}
