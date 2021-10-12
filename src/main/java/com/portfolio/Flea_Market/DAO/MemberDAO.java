package com.portfolio.Flea_Market.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.portfolio.Flea_Market.VO.MemberVO;

public class MemberDAO {
	// dao : 데이터베이스 접근 객체의 약자로써
	// 실질적으로 db에서 회원정보를 불러오거나 db에 회원정보 넣을때
	
	private Connection conn; // connection:jb에 접근하게 해주는 객체
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// mysql에 접속해 주는 부분
	public MemberDAO() { //생성자 실행될때마다 자동으로 db 연결이 이루어 질수 있있도록함
		try {
			String dbURL="";
			dbURL= "jdbc:mysql://localhost:3307/flea_market";
			String dbID = "root";
			String dbPassword = "***qweasd010236";
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
		
	
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			System.out.println(5);
		
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	//로그인을 시도하는 함수
	public int login(String userID, String userPassword) {
		String SQL = "select password from tb_member where email=?";
		try {
			// pstmt : prepared statememt 정해진 sql문장을 db에 삽입하는 형식으로 인스턴스 가져옴
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String pw=rs.getNString(1);
				if(pw.equals(userPassword)) {
					return 1;
				}
				else {return 0;}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //아이디가 없음 오류
	}
	
	public int join(MemberVO user) {
		String SQL = "INSERT INTO TB_MEMBER VALUES (?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setNString(1,  user.getEMAIL());
			pstmt.setNString(2,  user.getPASSWORD());
			pstmt.setNString(3,  user.getNAME());
			pstmt.setNString(4,  user.getNICKNAME());
			pstmt.setNString(5,  user.getPHONENUMBER());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB오류
		}
	}

