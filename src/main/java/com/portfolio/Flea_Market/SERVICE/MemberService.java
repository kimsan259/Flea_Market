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
	 * 비밀번호를 찾기 위한 이메일 유무 파악
	 * @param memberVo
	 * @return
	 * @throws Exception
	 */
	public MemberVO findEmail(MemberVO memberVo) throws Exception;
	
	/**
	 * 비밀번효 변경
	 * @param memberVo
	 * @return
	 * @throws Exception
	 */
	public int pwUpdate(MemberVO memberVo) throws Exception;
}
