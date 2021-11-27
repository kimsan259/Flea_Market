package com.portfolio.Flea_Market.DAO.IMPL;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.portfolio.Flea_Market.DAO.ReplyDAO;
import com.portfolio.Flea_Market.VO.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Inject SqlSession sql;
	
	// 댓글 조회
	@Override
	public List<ReplyVO> readReply(int BOARD_NUMBER) throws Exception {
		return sql.selectList("replyMapper.readReply", BOARD_NUMBER);
	
	}
	//댓글 작성
	@Override
	public void writeReply(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		sql.insert("replyMapper.writeReply", vo);
		
	}
}
