package com.codeones.warehouse.service;

import java.io.IOException;
import java.util.List;

import com.codeones.warehouse.entity.Document;

import jakarta.servlet.http.HttpServletResponse;

public interface IDocumentService {
	void saveDocument(Document document);
	List<Object[]> getDocIdAndName();
	Document getDocumentById(Long id);
	void deleteDocument(Long id);
	void downloadDocument(Long id, HttpServletResponse response) throws IOException;
}
