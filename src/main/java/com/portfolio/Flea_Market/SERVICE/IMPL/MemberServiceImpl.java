package com.portfolio.Flea_Market.SERVICE.IMPL;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.Flea_Market.DAO.MemberDAO;
import com.portfolio.Flea_Market.SERVICE.MemberService;
import com.portfolio.Flea_Market.VO.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO dao;

	@Override
	public MemberVO login(MemberVO memberVo) throws Exception {
		return dao.login(memberVo);
	}

	@Override
	public void join(MemberVO memberVo) throws Exception {
		dao.join(memberVo);
	}

	@Override
	public MemberVO findEmail(MemberVO memberVo) throws Exception {
		return dao.findEmail(memberVo);
	}

	@Override
	public int pwUpdate(MemberVO memberVo) throws Exception {
		return dao.pwUpdate(memberVo);
	}
	//Controller에서 보내는 파라미터들을 memberUpdate(MemberVO vo)로 받고
	@Override
	public void memberUpdate(MemberVO vo) throws Exception {
		
		//받은 vo를 DAO로 보내줍니다.
		dao.memberUpdate(vo);	
	}
		// 업데이트에서 처리한 내용과 같습니다.
	@Override
	public void memberDelete(MemberVO vo) throws Exception {
		dao.memberDelete(vo);
	}
	// 패스워드 체크
	@Override
	public int passChk(MemberVO vo) throws Exception {
		int result = dao.passChk(vo);
		return result;
	}
	// 아이디 중복 체크
	@Override
	public int idChk(MemberVO vo) throws Exception {
		int result = dao.idChk(vo);
		return result;
	}
	
	
}
