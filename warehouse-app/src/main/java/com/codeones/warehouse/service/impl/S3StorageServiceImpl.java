package com.codeones.warehouse.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.codeones.warehouse.service.IS3StorageService;

@Service
public class S3StorageServiceImpl implements IS3StorageService {

	private static final Logger LOG = LoggerFactory.getLogger(S3StorageServiceImpl.class);
	
	@Value("${application.bucket.name}")
	private String bucketName;
	
	@Autowired
	private AmazonS3 s3Client;
	
	@Override
	public String uploadFile(MultipartFile file) {
		//1. Convert MultipartFile to File
		File fileObj = convertMultipartFileToFile(file);
		
		//2. Generate File name
		String fileName = System.currentTimeMillis()+"-"+file.getOriginalFilename();
		
		//3. upload file using putObject() method of AmazonS3 class
		s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
		
		//4. recommended to delete fileObj after its use
		fileObj.delete();

		return "FILE '"+fileName+"' UPLOADED SUCCESSFULLY!";
	}
	
	private File convertMultipartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try(FileOutputStream fos = new FileOutputStream(convertedFile)){
			fos.write(file.getBytes());
		}catch (IOException ioe) {
			LOG.error("Error converting MultipartFile to File!");
		}
		return convertedFile;
	}

}
