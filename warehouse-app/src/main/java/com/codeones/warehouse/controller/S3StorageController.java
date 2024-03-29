package com.codeones.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codeones.warehouse.service.IS3StorageService;

@RestController
@RequestMapping("/s3/file")
public class S3StorageController {
	
	@Autowired
	private IS3StorageService service;
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
		return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK);
	}
}
