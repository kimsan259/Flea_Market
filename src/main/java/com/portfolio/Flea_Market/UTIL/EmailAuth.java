package com.portfolio.Flea_Market.UTIL;

import javax.mail.PasswordAuthentication;

import javax.mail.Authenticator;

public class EmailAuth extends Authenticator{

	PasswordAuthentication pwAuth;
	
	
	//1. ���� ���� Gmail �� �α����� ��(������� ������ �̸�����)
	public EmailAuth() {
		//���̵�
		String mailId = "fleamarket1111@gmail.com";
		
		//��й�ȣ
		String mailPw = "5463324zx";
		
		pwAuth = new PasswordAuthentication(mailId, mailPw);
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return pwAuth;
	}
}
