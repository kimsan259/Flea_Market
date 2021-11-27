package com.portfolio.Flea_Market.SERVICE.IMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.Flea_Market.DAO.CommentDAO;
import com.portfolio.Flea_Market.SERVICE.CommentService;
import com.portfolio.Flea_Market.VO.CommentVO;

@Service("commentService")
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentDAO dao;

	@Override
	public List<CommentVO> selectCommentList(CommentVO vo) throws Exception {
		return dao.selectCommentList(vo);
	}

	@Override
	public void writeReply(CommentVO vo) throws Exception {
		dao.writeReply(vo);
	}

	@Override
	public void updateReply(CommentVO vo) throws Exception {
		dao.updateReply(vo);
	}

	@Override
	public void deleteReply(CommentVO vo) throws Exception {
		dao.deleteReply(vo);
	}
	
}
