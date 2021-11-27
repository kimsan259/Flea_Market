package com.portfolio.Flea_Market.DAO.IMPL;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portfolio.Flea_Market.DAO.MemberDAO;
import com.portfolio.Flea_Market.VO.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberVO login(MemberVO memberVo) throws Exception {
		return sqlSession.selectOne("memberMapper.login", memberVo);
	}

	@Override
	public void join(MemberVO memberVo) throws Exception {
		sqlSession.insert("memberMapper.join", memberVo);
	}

	@Override
	public MemberVO findEmail(MemberVO memberVo) throws Exception {
		return sqlSession.selectOne("memberMapper.findEmail", memberVo);
	}

	@Override
	public int pwUpdate(MemberVO memberVo) throws Exception {
		return sqlSession.update("memberMapper.pwUpdate", memberVo);
	}
	//서비스에서 보낸 파라미터들을 memberUpdate(MemberVO vo)에 담습니다.
	@Override
	public void memberUpdate(MemberVO vo) throws Exception {
		// vo에 담긴 파라미터들은 memberMapper.xml에 memberMapper라는 namespace에 
		// 아이디가 memberUpdate인 쿼리에 파라미터들을 넣어줍니다.
		sqlSession.update("memberMapper.memberUpdate", vo); 
	}
	
}
