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
	// 게시글 작성
	@Override
	public void write(BoardVO boardVo) throws Exception {
		dao.write(boardVo);
	}
	// 게시글 목록 조회
	public List<BoardVO> list() throws Exception {

		return dao.list();
	}
	
}
