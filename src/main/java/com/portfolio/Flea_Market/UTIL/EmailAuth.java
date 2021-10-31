package com.portfolio.Flea_Market.UTIL;

import javax.mail.PasswordAuthentication;

import javax.mail.Authenticator;

public class EmailAuth extends Authenticator{

	PasswordAuthentication pwAuth;
	
	
	//1. 구글 계정 Gmail 로 로그인을 함(벼룩시작 관리자 이메일임)
	public EmailAuth() {
		//아이디
		String mailId = "fleamarket1111@gmail.com";
		
		//비밀번호
		String mailPw = "5463324zx";
		
		pwAuth = new PasswordAuthentication(mailId, mailPw);
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return pwAuth;
	}
}
