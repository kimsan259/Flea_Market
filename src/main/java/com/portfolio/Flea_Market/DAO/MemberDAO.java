package com.portfolio.Flea_Market.DAO;

import com.portfolio.Flea_Market.VO.MemberVO;

public interface MemberDAO {

	/**
	 * 로그인
	 * @param memberVo
	 * @return
	 * @throws Exception
	 */
	public MemberVO login(MemberVO memberVo) throws Exception;
	
	/**
	 * 가입하기
	 * @param memberVo
	 * @throws Exception
	 */
	public void join(MemberVO memberVo) throws Exception;
}
