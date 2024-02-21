package com.codeones.warehouse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.internet.MimeMessage;

@Component
public class MailUtil {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendEmail(
				String to,
				String[] cc,
				String[] bcc,
				String subject,
				String text,
				MultipartFile file ) 
	{
		boolean flag = false;
		try {
			//1. crate one new empty mime message using javaMailSender(I)
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			
			//2. set values to this empty mime message using mimeHelper
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, file!=null);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			
			if(cc != null && cc.length > 0)
				helper.setCc(cc);
			if(bcc != null && bcc.length > 0)
				helper.setBcc(bcc);
			
			if(file != null)
				helper.addAttachment(file.getOriginalFilename(), file);
			
			//3.send email
			javaMailSender.send(mimeMessage);
			flag = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public boolean sendEmail(
			String to,
			String subject,
			String text) 
	{
		return sendEmail(to, null, null, subject, text, null);
	}
	
	public boolean sendEmail(
			String to,
			String subject,
			String text,
			MultipartFile file
			) 
	{
		return sendEmail(to, subject, text, file);
	}
}
