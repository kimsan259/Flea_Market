package com.portfolio.Flea_Market.DAO;

import com.portfolio.Flea_Market.VO.BoardVO;

public interface BoardDAO {

	/**
	 * 게시판 글쓰기
	 * @param boardVo
	 * @throws Exception
	 */
	public void write(BoardVO boardVo) throws Exception;
}
