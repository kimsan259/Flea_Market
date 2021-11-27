package com.portfolio.Flea_Market.VO;

import java.util.Date;

public class ReplyVO {

	private int NUMBER;
	private String UPPER_NUMBER;
	private String BOARD_NUMBER;
	private String MEMBER_NICKNAME;
	private String MASTER_NICKNAME;;
	private String CONTENT;
	private String REGIST_DATE;
	
	public int getNUMBER() {
		return NUMBER;
	}
	public void setNumber(int NUMBER) {
		this.NUMBER = NUMBER;
	}
	public String getUPPER_NUMBER() {
		return UPPER_NUMBER;
	}
	public void setUPPER_NUMBER(String UPPER_NUMBER) {
		this.UPPER_NUMBER = UPPER_NUMBER;
	}
	public String getBOARD_NUMBER() {
		return UPPER_NUMBER;
	}
	public void setBOARD_NUMBER(String BOARD_NUMBER) {
		this.BOARD_NUMBER = BOARD_NUMBER;
	}
	public String getMEMBER_NICKNAME() {
		return MEMBER_NICKNAME;
	}
	public void setMEMBER_NICKNAME(String MEMBER_NICKNAME) {
		this.MEMBER_NICKNAME = MEMBER_NICKNAME;
	}
	public String getMASTER_NICKNAME() {
		return MASTER_NICKNAME;
	}
	public void setMASTER_NICKNAME(String MASTER_NICKNAME) {
		this.MASTER_NICKNAME = MASTER_NICKNAME;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String CONTENT) {
		this.CONTENT = CONTENT;
	}
	public String getREGIST_DATE() {
		return REGIST_DATE;
	}
	public void setREGIST_DATE(String REGIST_DATE) {
		this.REGIST_DATE = REGIST_DATE;
	}
	@Override
	public String toString() {
		return "ReplyVO [NUMBER=" + NUMBER + ", UPPER_NUMBER=" + UPPER_NUMBER+ ", BOARD_NUMBER=" + BOARD_NUMBER + ", MEMBER_NICKNAME=" + MEMBER_NICKNAME + ", MASTER_NICKNAME="
				+ MASTER_NICKNAME + ", CONTENT=" + CONTENT + ", REGIST_DATE= "+REGIST_DATE+"]";
	}
	
	
}