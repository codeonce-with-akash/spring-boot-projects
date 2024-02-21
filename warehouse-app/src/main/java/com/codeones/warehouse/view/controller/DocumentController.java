package com.codeones.warehouse.view.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.codeones.warehouse.entity.Document;
import com.codeones.warehouse.exception.DocumentNotFoundException;
import com.codeones.warehouse.service.IDocumentService;
import com.codeones.warehouse.util.AppUtil;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/v1/document")
public class DocumentController {
	
	private static final Logger LOG = LoggerFactory.getLogger(DocumentController.class);
	
	@Autowired
	private IDocumentService documentService;
	
	//1. SHOW ALL DOCUMENTS
	@GetMapping("/all")
	public String showDocumentPage(Model model) {
		List<Object[]> list = documentService.getDocIdAndName();
		model.addAttribute("list", list);
		return "Documents";
	}
	
	//2. UPLOAD
	@PostMapping("/upload")
	public String uploadDocument(@RequestParam MultipartFile docOb) {
		Document doc = new Document();
		try {
			doc.setDocId(System.currentTimeMillis());
			doc.setDocName(docOb.getOriginalFilename());
			doc.setDocData(docOb.getBytes());
			documentService.saveDocument(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:all";
	}
	
	//3. DELETE
	@GetMapping("/delete")
	public String deleteDocumentById(@RequestParam Long id) {
		LOG.info("ABOUT TO ENTERED INTO DocumentController::deleteDocumentById(-) METHOD");
		try {
			LOG.debug("Success - Document Deleted Successfully!");
			documentService.deleteDocument(id);
		} catch (DocumentNotFoundException dnfe) {
			LOG.error("Exception - {}", AppUtil.getLogSupport(dnfe));
			dnfe.printStackTrace();
		}
		return "redirect:all";
	}
	
	//4. DOWNLOAD
	@GetMapping("/download")
	public void downloadDocument(@RequestParam Long id, HttpServletResponse response) {
		try {
			documentService.downloadDocument(id, response);
		}catch (DocumentNotFoundException dnfe) {
			LOG.error("Exception - {}", AppUtil.getLogSupport(dnfe));
		} catch (IOException ioe) {
			LOG.error("Exception - {}", AppUtil.getLogSupport(ioe));
		} catch (Exception e) {
			LOG.error("Exception - {}", AppUtil.getLogSupport(e));
		}
	}
}
