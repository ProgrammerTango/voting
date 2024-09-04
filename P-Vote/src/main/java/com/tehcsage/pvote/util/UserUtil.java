package com.tehcsage.pvote.util;

import java.util.Properties;

import java.util.Random;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;


public class UserUtil {
	public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        return sb.toString();
    }
	
	public static String getUsername() {
//	     return "agumei@postbank.co.ke";
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	       if (authentication != null) {
	           Object principal = authentication.getPrincipal();
	           if (principal instanceof UserDetails) {
	               return ((UserDetails) principal).getUsername();
	           } else {
	               return principal.toString();
	           }
	       }
	       return "Unknown User";
	   }
	
	 public static void sendEmail(String to, String subject, String body) {
	        // Set up the mail server properties
		 Properties properties = new Properties();
		 properties.put("mail.smtp.host", "smtp.gmail.com");
		 properties.put("mail.smtp.port", "465");
		 properties.put("mail.smtp.auth", "true");
		 properties.put("mail.smtp.socketFactory.port", "465");
		 properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 properties.put("mail.smtp.socketFactory.fallback", "false");
		 properties.put("mail.smtp.ssl.enable", "true"); 

	        // Set up the authentication
//	        final String username = "oliwaian@gmail.com"; // Replace with your email
//	        final String password = "sucdjxkkjxreibxp"; // Replace with your password
//		 
//		 final String username = "techsage360.info@gmail.com";
//		 final String password = "nnqzzvjnguddmanm";
		 
		 	final String username = "agumeian96@gmail.com";
		 	final String password = "jsrauyytikjbquje";

	        Session session = Session.getInstance(properties, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	        });

	        try {
	            // Create a new email message
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(username));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	            message.setSubject(subject);
	            message.setText(body);

	            // Send the email
	            Transport.send(message);

	            System.out.println("Email sent successfully!");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
}
