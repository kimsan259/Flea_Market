package com.portfolio.Flea_Market.VO;

public class CommentVO extends PageVO{
	private int NUMBER; 
	private String UPPER_NUMBER;
	private String BOARD_NUMBER;
	private String MEMBER_NICKNAME;
	private String MASTER_NICKNAME;
	private String CONTENT;
	private String REGIST_DATE;

	public int getNUMBER() {
		return NUMBER;
	}
	public void setNUMBER(int nUMBER) {
		NUMBER = nUMBER;
	}
	public String getUPPER_NUMBER() {
		return UPPER_NUMBER;
	}
	public void setUPPER_NUMBER(String uPPER_NUMBER) {
		UPPER_NUMBER = uPPER_NUMBER;
	}
	public String getBOARD_NUMBER() {
		return BOARD_NUMBER;
	}
	public void setBOARD_NUMBER(String bOARD_NUMBER) {
		BOARD_NUMBER = bOARD_NUMBER;
	}
	public String getMEMBER_NICKNAME() {
		return MEMBER_NICKNAME;
	}
	public void setMEMBER_NICKNAME(String mEMBER_NICKNAME) {
		MEMBER_NICKNAME = mEMBER_NICKNAME;
	}
	public String getMASTER_NICKNAME() {
		return MASTER_NICKNAME;
	}
	public void setMASTER_NICKNAME(String mASTER_NICKNAME) {
		MASTER_NICKNAME = mASTER_NICKNAME;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getREGIST_DATE() {
		return REGIST_DATE;
	}
	public void setREGIST_DATE(String rEGIST_DATE) {
		REGIST_DATE = rEGIST_DATE;
	}

}
