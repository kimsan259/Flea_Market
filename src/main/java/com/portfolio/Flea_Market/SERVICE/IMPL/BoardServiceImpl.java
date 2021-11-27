package com.portfolio.Flea_Market.SERVICE.IMPL;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.Flea_Market.DAO.BoardDAO;
import com.portfolio.Flea_Market.SERVICE.BoardService;
import com.portfolio.Flea_Market.VO.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO dao;
	// 寃뚯떆湲� �옉�꽦
	@Override
	public void write(BoardVO boardVo) throws Exception {
		dao.write(boardVo);
	}
	// 寃뚯떆湲� 紐⑸줉 議고쉶
	public List<BoardVO> list(BoardVO vo) throws Exception {

		return dao.list(vo);
	}
	// 寃뚯떆臾� �옄�꽭�엳
	@Override
	public BoardVO read(int NUMBER) throws Exception {

		return dao.read(NUMBER);
	}
	
	// 寃뚯떆湲� �닔�젙
	@Override
	public void update(BoardVO boardVO) throws Exception {

		dao.update(boardVO);
	}
	
	// 寃뚯떆湲� �궘�젣
	@Override
	public void delete(int NUMBER) throws Exception {
		
		dao.delete(NUMBER);
	}
}
