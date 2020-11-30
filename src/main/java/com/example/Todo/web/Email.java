package com.example.Todo.web;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;


public class Email {
	
	//@Value("#{environment.SPRING_DATASOURCE_ADDRESS}")
	//private String address;
	//@Value("#{environment.SPRING_DATASOURCE_PASS}")
	//private String pass;
	
	public void sendEmail(String to, String subject, String text) {
		
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("noreplyfakeacc@gmail.com");
	    mailSender.setPassword("qpgrwtslnoazhvem");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");

	Session session = Session.getDefaultInstance(props);

	try {
		
		MimeMessage message = new MimeMessage(session);
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		message.setFrom(new InternetAddress("NoReplyFakeAcc@gmail.com"));
		
		helper.setFrom("noreply@todo.com");
	    helper.setTo(to);
	    helper.setSubject(subject);
	    helper.setText(text);
	    
	    mailSender.send(message);
		
	} catch (MessagingException mex) {
		mex.printStackTrace();
	}
}}