package com.portfolio.Flea_Market.UTIL;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

	public void mailSend(String email, String pw) {
		
		// 占싱몌옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙
		Properties prop = System.getProperties();

	        prop.put("mail.smtp.starttls.enable", "true");

	        prop.put("mail.smtp.host", "smtp.gmail.com");

	        prop.put("mail.smtp.auth", "true");

	        prop.put("mail.smtp.port", "587");
	        
	        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	        
	        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

	        
	    // end 占싱몌옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙

	        //2. 占싣깍옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙체 占쏙옙占쏙옙占쏙옙 (占싸몌옙占� 占쌘쏙옙 占쏙옙占쏙옙占� 占싱뤄옙占쏙옙 占쏙옙체 占쏙옙占쏙옙 占쏙옙占쏙옙)
	        //占쏙옙占쏙옙 EmailAuth auth = new EmailAuth(); 占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占싸몌옙 占쏙옙체占쏙옙 占십울옙占쌔쇽옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙
	        Authenticator auth = new EmailAuth();

	        // 占싱몌옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙티占� 
	        Session session = Session.getDefaultInstance(prop, auth);

	        // 占쏙옙占쏙옙占� 占쏙옙占쏙옙
	        // 占싱곤옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쌉뤄옙占싹울옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙체占쏙옙
	        MimeMessage msg = new MimeMessage(session);

	        try {
	        	
	        	//占쏙옙占쏙옙占쏙옙 占쏙옙짜
	            msg.setSentDate(new Date());

	            //占쏙옙占쏙옙占쏙옙 占쏙옙占�
	            msg.setFrom(new InternetAddress("fleamarket@gmail.com", "ADMIN"));

	            //占쌨댐옙 占쏙옙占� 占쏙옙占쏙옙
	            InternetAddress to = new InternetAddress(email);         

	            //占쌨댐옙 占쏙옙占� 占싱몌옙占쏙옙
	            msg.setRecipient(Message.RecipientType.TO, to);            

	            //占쌨댐옙 占쏙옙占� 占쏙옙占쏙옙
	            msg.setSubject("임시비밀번호 발송내역입니다..","UTF-8");           

	            //占쌨댐옙 占쏙옙占� 占쏙옙占쏙옙
	            msg.setText("임시비밀번호 : " + pw + "  로그인 후 수정부탁드립니다.", "UTF-8");
	            
	            //占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙
	            Transport.send(msg);


	        } catch(AddressException ae) {            

	            System.out.println("AddressException : " + ae.getMessage());           

	        } catch(MessagingException me) {            

	            System.out.println("MessagingException : " + me.getMessage());

	        } catch(UnsupportedEncodingException e) {

	            System.out.println("UnsupportedEncodingException : " + e.getMessage());

	        }


	}
}
