package com.codeones.warehouse.service;

import org.springframework.web.multipart.MultipartFile;

public interface IS3StorageService {
	
	public String uploadFile(MultipartFile file);
	
}
