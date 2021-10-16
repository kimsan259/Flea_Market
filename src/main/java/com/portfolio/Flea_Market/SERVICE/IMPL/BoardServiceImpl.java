package com.portfolio.Flea_Market.SERVICE.IMPL;

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

	@Override
	public void write(BoardVO boardVo) throws Exception {
		dao.write(boardVo);
	}
	
	
}
