package com.portfolio.Flea_Market.SERVICE;

import java.util.List;

import com.portfolio.Flea_Market.VO.CommentVO;

public interface CommentService {

	/**
	 * 답글 게시판 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<CommentVO> selectCommentList(CommentVO vo) throws Exception;
	
	/**
	 * 답글 작성
	 * @param vo
	 * @throws Exception
	 */
	public void writeReply(CommentVO vo) throws Exception;
	
	/**
	 * 답글 수정
	 * @param vo
	 * @throws Exception
	 */
	public void updateReply(CommentVO vo) throws Exception;
	
	/**
	 * 답글 삭제
	 * @param vo
	 * @throws Exception
	 */
	public void deleteReply(CommentVO vo) throws Exception;
}
