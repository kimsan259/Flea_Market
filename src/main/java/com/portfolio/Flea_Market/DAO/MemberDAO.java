package com.portfolio.Flea_Market.DAO;

import com.portfolio.Flea_Market.VO.MemberVO;

public interface MemberDAO {

	/**
	 濡쒓렇�씤
	 * @param memberVo
	 * @return
	 * @throws Exception
	 */
	public MemberVO login(MemberVO memberVo) throws Exception;
	
	/**
	 * 媛��엯�븯湲�
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
	// 비밀번호 바꾸기
	public int pwUpdate(MemberVO memberVo) throws Exception;
	
	// 회원정보 수정
	public void memberUpdate(MemberVO vo)throws Exception;
	
	
	//회원 탈퇴
	public void memberDelete(MemberVO vo)throws Exception;
	
	// 패스워드 체크
	public int passChk(MemberVO vo) throws Exception;
	
	// 아이디 중복체크
	public int idChk(MemberVO vo) throws Exception;
	
	

}
