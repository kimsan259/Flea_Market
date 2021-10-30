package com.portfolio.Flea_Market.SERVICE;

import java.util.List;

import com.portfolio.Flea_Market.VO.BoardVO;

public interface BoardService {

	/**
	 * 게시판 글쓰기
	 * @param boardVo
	 * @throws Exception
	 */
	public void write(BoardVO boardVo) throws Exception;
	
	// 게시글 목록 조회
	public List<BoardVO> list() throws Exception;
	
	// 게시판 자세히 보기
	public BoardVO read(int NUMBER) throws Exception;
}
