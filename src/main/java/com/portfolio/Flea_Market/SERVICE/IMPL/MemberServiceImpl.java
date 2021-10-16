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
}
