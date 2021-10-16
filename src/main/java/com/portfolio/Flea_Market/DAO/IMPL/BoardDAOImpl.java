package com.portfolio.Flea_Market.DAO.IMPL;

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

	@Override
	public void write(BoardVO boardVo) throws Exception {
		sqlSession.insert("boardMapper.insert", boardVo);
	}
}
