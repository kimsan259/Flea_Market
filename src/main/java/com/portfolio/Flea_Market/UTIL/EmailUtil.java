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
		
		// 이메일 보내기 기능 세팅
		Properties prop = System.getProperties();

	        prop.put("mail.smtp.starttls.enable", "true");

	        prop.put("mail.smtp.host", "smtp.gmail.com");

	        prop.put("mail.smtp.auth", "true");

	        prop.put("mail.smtp.port", "587");
	        
	        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	        
	        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

	        
	    // end 이메일 보내기 기능 세팅

	        //2. 아까 세팅한 구글 계정 객체 보내줌 (부모와 자식 관계로 이렇게 객체 생성 가능)
	        //원랜 EmailAuth auth = new EmailAuth(); 도 가능하지만 부모 객체도 필요해서 저렇게 선언한 것
	        Authenticator auth = new EmailAuth();

	        // 이메일 보내기 기능과 메일 보낼 계정을 담아서 
	        Session session = Session.getDefaultInstance(prop, auth);

	        // 여기다 세팅
	        // 이것은 메일 보낼때 내용 입력하여 보내기 위한 객체임
	        MimeMessage msg = new MimeMessage(session);

	        try {
	        	
	        	//보내는 날짜
	            msg.setSentDate(new Date());

	            //보내는 사람
	            msg.setFrom(new InternetAddress("fleamarket@gmail.com", "ADMIN"));

	            //받는 사람 세팅
	            InternetAddress to = new InternetAddress(email);         

	            //받는 사람 이메일
	            msg.setRecipient(Message.RecipientType.TO, to);            

	            //받는 사람 제목
	            msg.setSubject("안녕하세요 벼룩시장입니다.", "UTF-8");            

	            //받는 사람 내용
	            msg.setText("임시비밀번호는 " + pw + "입니다. \n 비밀번호는 변경하실 수 없으니 기억해주세요", "UTF-8");            

	            
	            //최종적으로 메일 보내기
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
