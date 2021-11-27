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
		
		// �̸��� ������ ��� ����
		Properties prop = System.getProperties();

	        prop.put("mail.smtp.starttls.enable", "true");

	        prop.put("mail.smtp.host", "smtp.gmail.com");

	        prop.put("mail.smtp.auth", "true");

	        prop.put("mail.smtp.port", "587");
	        
	        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	        
	        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

	        
	    // end �̸��� ������ ��� ����

	        //2. �Ʊ� ������ ���� ���� ��ü ������ (�θ�� �ڽ� ����� �̷��� ��ü ���� ����)
	        //���� EmailAuth auth = new EmailAuth(); �� ���������� �θ� ��ü�� �ʿ��ؼ� ������ ������ ��
	        Authenticator auth = new EmailAuth();

	        // �̸��� ������ ��ɰ� ���� ���� ������ ��Ƽ� 
	        Session session = Session.getDefaultInstance(prop, auth);

	        // ����� ����
	        // �̰��� ���� ������ ���� �Է��Ͽ� ������ ���� ��ü��
	        MimeMessage msg = new MimeMessage(session);

	        try {
	        	
	        	//������ ��¥
	            msg.setSentDate(new Date());

	            //������ ���
	            msg.setFrom(new InternetAddress("fleamarket@gmail.com", "ADMIN"));

	            //�޴� ��� ����
	            InternetAddress to = new InternetAddress(email);         

	            //�޴� ��� �̸���
	            msg.setRecipient(Message.RecipientType.TO, to);            

	            //�޴� ��� ����
	            msg.setSubject("안녕하세요 벼룩시장입니다.","UTF-8");           

	            //�޴� ��� ����
	            msg.setText("비밀번호는" + pw + "입니다.", "UTF-8");
	            
	            //���������� ���� ������
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
