package com.portfolio.Flea_Market.SERVICE;

import java.util.List;

import com.portfolio.Flea_Market.VO.ReplyVO;

public interface ReplyService {
	
	//댓글 조회
	public List<ReplyVO> readReply(int BOARD_NUMBER) throws Exception;
	//댓글 작성
	public void writeReply(ReplyVO vo) throws Exception;
	
}