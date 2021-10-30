package com.portfolio.Flea_Market.DAO;

import java.util.List;

import com.portfolio.Flea_Market.VO.BoardVO;

public interface BoardDAO {

	/**
	 * 게시판 글쓰기
	 * @param boardVo
	 * @throws Exception
	 */
	// 게시글 작성
	public void write(BoardVO boardVo) throws Exception;
	
	// 게시물 목록 조회
	public List<BoardVO> list() throws Exception;

}
