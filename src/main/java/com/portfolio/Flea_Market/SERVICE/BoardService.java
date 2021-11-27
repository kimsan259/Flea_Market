package com.portfolio.Flea_Market.SERVICE;

import java.util.List;

import com.portfolio.Flea_Market.VO.BoardVO;

public interface BoardService {

	/**
	 * 寃뚯떆�뙋 湲��벐湲�
	 * @param boardVo
	 * @throws Exception
	 */
	public void write(BoardVO boardVo) throws Exception;
	
	// 寃뚯떆湲� 紐⑸줉 議고쉶
	public List<BoardVO> list(BoardVO vo) throws Exception;
	
	// 寃뚯떆�뙋 �옄�꽭�엳 蹂닿린
	public BoardVO read(int NUMBER) throws Exception;
	
	// 寃뚯떆臾� �닔�젙
	public void update(BoardVO boardVO) throws Exception;
	
	// 寃뚯떆臾� �궘�젣
	public void delete(int NUMBER) throws Exception;
}
