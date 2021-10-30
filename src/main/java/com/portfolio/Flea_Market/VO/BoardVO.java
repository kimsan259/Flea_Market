package com.portfolio.Flea_Market.VO;

public class BoardVO {

	private int NUMBER;
	private String TITLE;
	private String PRICE;
	private String REGION;
	private String CONTENT;
	private String MASTER_NICKNAME;
	private String MASTER_EMAIL;
	private String REGIST_DATE;
	
	public String getREGIST_DATE() {
		return REGIST_DATE;
	}
	public void setREGIST_DATE(String rEGIST_DATE) {
		REGIST_DATE = rEGIST_DATE;
	}
	public int getNUMBER() {
		return NUMBER;
	}
	public void setNUMBER(int nUMBER) {
		NUMBER = nUMBER;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getPRICE() {
		return PRICE;
	}
	public void setPRICE(String pRICE) {
		PRICE = pRICE;
	}
	public String getREGION() {
		return REGION;
	}
	public void setREGION(String rEGION) {
		REGION = rEGION;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	
	public String getMASTER_NICKNAME() {
		return MASTER_NICKNAME;
	}
	public void setMASTER_NICKNAME(String mASTER_NICKNAME) {
		MASTER_NICKNAME = mASTER_NICKNAME;
	}
	public String getMASTER_EMAIL() {
		return MASTER_EMAIL;
	}
	public void setMASTER_EMAIL(String mASTER_EMAIL) {
		MASTER_EMAIL = mASTER_EMAIL;
	}
}