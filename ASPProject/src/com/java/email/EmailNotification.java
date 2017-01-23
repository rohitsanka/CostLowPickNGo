package com.java.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailservice")
public class EmailNotification {
	@Autowired
	private MailSender mailSender; 
	public void setMailSender(JavaMailSender mailSender) {  
        this.mailSender = mailSender;  
    } 
	
	 public void sendMail(final String to,final String msg) {  
	        try{  
	        /*MimeMessage message = mailSender.createMimeMessage();  
	  
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);  
	        helper.setFrom("sankarohit1@gmail.com");  
	        helper.setTo(to);  
	        helper.setSubject("Email Notification");  
	        helper.setText(msg,true); 
	  
	        mailSender.send(message);  */
	        	SimpleMailMessage message = new SimpleMailMessage();

	    		message.setFrom("sankarohit1@gmail.com");
	    		message.setTo(to);
	    	//	message.setSubject(subject);
	    		message.setText(msg);
	    		mailSender.send(message);
	    		System.out.println("message sent");
	        }catch(Exception e){e.printStackTrace();}  
	    }  
    
  
}
