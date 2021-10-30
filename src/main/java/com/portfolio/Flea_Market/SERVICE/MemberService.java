package com.portfolio.Flea_Market.SERVICE;

import com.portfolio.Flea_Market.VO.MemberVO;

public interface MemberService {

	/**
	 * 로그인
	 * @param memberVo
	 * @return
	 * @throws Exception
	 */
	public MemberVO login(MemberVO memberVo) throws Exception;
	
	/**
	 * 회원가입
	 * @param memberVo
	 * @throws Exception
	 */
	public void join(MemberVO memberVo) throws Exception;
}
