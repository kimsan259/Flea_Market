package com.portfolio.Flea_Market.SERVICE.IMPL;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.portfolio.Flea_Market.DAO.ReplyDAO;
import com.portfolio.Flea_Market.SERVICE.ReplyService;
import com.portfolio.Flea_Market.VO.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO dao;
	
	@Override
	public List<ReplyVO> readReply(int BOARD_NUMBER) throws Exception {
		return dao.readReply(BOARD_NUMBER);
	}
		
	@Override
	public void writeReply(ReplyVO vo) throws Exception {
		dao.writeReply(vo);
	}
	
}