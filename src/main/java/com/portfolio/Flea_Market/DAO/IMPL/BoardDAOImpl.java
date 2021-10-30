package com.portfolio.Flea_Market.DAO.IMPL;

import java.util.List;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portfolio.Flea_Market.DAO.BoardDAO;
import com.portfolio.Flea_Market.VO.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	// 게시글 작성
	@Override
	public void write(BoardVO boardVo) throws Exception {
		sqlSession.insert("boardMapper.insert", boardVo);
	}
	// 게시글 목록 조회
	public List<BoardVO> list() throws Exception {
		
		return sqlSession.selectList("boardMapper.list");
	}
}
