package com.portfolio.Flea_Market.DAO;

import java.util.List;

import com.portfolio.Flea_Market.VO.ReplyVO;

public interface ReplyDAO {
	
	//댓글 조회
	public List<ReplyVO> readReply(int BOARD_NUMBER) throws Exception;
	
	//댓글 작성
	public void writeReply(ReplyVO vo) throws Exception;
	
	

}