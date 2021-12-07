package com.portfolio.Flea_Market.SERVICE;

import com.portfolio.Flea_Market.VO.MemberVO;

public interface MemberService {

	/**
	 * 濡쒓렇�씤
	 * @param memberVo
	 * @return
	 * @throws Exception
	 */
	public MemberVO login(MemberVO memberVo) throws Exception;
	
	/**
	 * �쉶�썝媛��엯
	 * @param memberVo
	 * @throws Exception
	 */
	public void join(MemberVO memberVo) throws Exception;
	
	/**
	 * 鍮꾨�踰덊샇瑜� 李얘린 �쐞�븳 �씠硫붿씪 �쑀臾� �뙆�븙
	 * @param memberVo
	 * @return
	 * @throws Exception
	 */
	public MemberVO findEmail(MemberVO memberVo) throws Exception;
	
	/**
	 * 鍮꾨�踰덊슚 蹂�寃�
	 * @param memberVo
	 * @return
	 * @throws Exception
	 */
	public int pwUpdate(MemberVO memberVo) throws Exception;
	
	public void memberUpdate(MemberVO vo) throws Exception;
	
	public void memberDelete(MemberVO vo) throws Exception;
	
	public int passChk(MemberVO vo) throws Exception;
	
	public int idChk(MemberVO vo) throws Exception;
}
