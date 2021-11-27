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
	// 寃뚯떆湲� �옉�꽦
	@Override
	public void write(BoardVO boardVo) throws Exception {
		sqlSession.insert("boardMapper.insert", boardVo);
	}
	// 寃뚯떆湲� 紐⑸줉 議고쉶
	public List<BoardVO> list(BoardVO vo) throws Exception {
		
		return sqlSession.selectList("boardMapper.list", vo);
	}
	// 寃뚯떆臾� 議고쉶
	@Override
	public BoardVO read(int NUMBER) throws Exception {
			
		return sqlSession.selectOne("boardMapper.read", NUMBER);
	}
	// 寃뚯떆臾� �닔�젙
	@Override
	public void update(BoardVO boardVO) throws Exception {
		
		sqlSession.update("boardMapper.update", boardVO);
	}

	// 寃뚯떆臾� �궘�젣
	@Override
	public void delete(int NUMBER) throws Exception {
		
		sqlSession.delete("boardMapper.delete", NUMBER);
	}

	
}
