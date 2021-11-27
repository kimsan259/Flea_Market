package com.portfolio.Flea_Market.DAO;

import java.util.List;

import com.portfolio.Flea_Market.VO.CommentVO;
import com.portfolio.Flea_Market.VO.ReplyVO;

public interface CommentDAO {

	/**
	 * ��� �Խ��� ��ȸ
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<CommentVO> selectCommentList(CommentVO vo) throws Exception;
	
	/**
	 * ��� �ۼ�
	 * @param vo
	 * @throws Exception
	 */
	public void writeReply(CommentVO vo) throws Exception;
	
	/**
	 * ��� ����
	 * @param vo
	 * @throws Exception
	 */
	public void updateReply(CommentVO vo) throws Exception;
	
	/**
	 * ��� ����
	 * @param vo
	 * @throws Exception
	 */
	public void deleteReply(CommentVO vo) throws Exception;
}
