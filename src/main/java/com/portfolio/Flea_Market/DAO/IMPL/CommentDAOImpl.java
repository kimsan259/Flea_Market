package com.portfolio.Flea_Market.DAO.IMPL;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portfolio.Flea_Market.DAO.CommentDAO;
import com.portfolio.Flea_Market.VO.CommentVO;

@Repository
public class CommentDAOImpl implements CommentDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<CommentVO> selectCommentList(CommentVO vo) throws Exception {
		return sqlSession.selectList("commentMapper.selectCommentList", vo);
	}

	@Override
	public void writeReply(CommentVO vo) throws Exception {
		sqlSession.insert("commentMapper.writeReply", vo);
	}

	@Override
	public void updateReply(CommentVO vo) throws Exception {
		sqlSession.update("commentMapper.updateReply", vo);
	}

	@Override
	public void deleteReply(CommentVO vo) throws Exception {
		sqlSession.delete("commentMapper.deleteReply", vo);
	}
	
}
