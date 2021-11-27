package com.portfolio.Flea_Market.DAO;

import com.portfolio.Flea_Market.VO.MemberVO;

public interface MemberDAO {

	/**
	 로그인
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
	
	// 회원정보 수정
	public void memberUpdate(MemberVO vo)throws Exception;
	    
	

}
