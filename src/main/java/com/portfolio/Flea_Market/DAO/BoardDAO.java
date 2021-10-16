package com.portfolio.Flea_Market.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.portfolio.Flea_Market.VO.BoardVO;
import com.portfolio.Flea_Market.VO.MemberVO;

public class BoardDAO {

	private Connection conn;
	private ResultSet rs;
	
	//기본 생성자
	public BoardDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3307/flea_market";
			String dbID = "root";
			String dbPassword = "***qweasd010236";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	//작성일자 메소드
	public String getDate() {
		String sql = "select now()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ""; //데이터베이스 오류
	}
	
	
	//글쓰기 메소드
		public int write(BoardVO user) {
			String sql = "insert into bbs values(?, ?, ?, ?, ?, ?)";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getTITLE());
				pstmt.setString(2, user.getPRICE());
				pstmt.setString(3, user.getREGION());
				pstmt.setString(4, user.getCONTENT());
				pstmt.setString(5, user.getMASTER_NICKNME());
				pstmt.setString(6, user.getMASTER_EMAIL());
				return pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return -1; //데이터베이스 오류
		}
	    
	}