package com.imdad.utility;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	JavaMailSender mailSender;
	
	public boolean sendMail(String to, String subject, String body, File f) {
		
		try {
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);
			helper.addAttachment("Plans.xlsx", f);
			mailSender.send(mimeMessage);
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		 
		return true;
		
	}

}
