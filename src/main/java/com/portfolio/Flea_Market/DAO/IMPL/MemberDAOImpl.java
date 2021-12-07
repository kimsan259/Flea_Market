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
	// 서비스에서 보낸 파라미터들을 memberUpdate(MemberVO vo)에 담습니다.
	@Override
	public void memberUpdate(MemberVO vo) throws Exception {
		// vo에 담긴 파라미터들을 memberMapper.xml에 memberMapper 라는 namespace에 
		// 아이디가 memberUpdate인 쿼리에 파라미터들을 넣어줍니다.
		sqlSession.update("memberMapper.memberUpdate", vo); 
	}
		// 업데이트와 마찬가지로 흐름은 같습니다.
	@Override
	public void memberDelete(MemberVO vo) throws Exception {
		// MemberVO에 담긴 값들을 보내줍니다.
		// 그럼 xml에서 memberMapper.memberDelete에 보시면
		//  #{userId}, #{userPass}에 파라미터값이 매칭이 되겠지요.
		sqlSession.delete("memberMapper.memberDelete", vo);
			
	}
	// 패스워드 체크
	@Override
	public int passChk(MemberVO vo) throws Exception {
		int result = sqlSession.selectOne("memberMapper.passChk", vo);
		return result;
	}
	
	// 아이디 중복 체크
	@Override
	public int idChk(MemberVO vo) throws Exception {
		int result = sqlSession.selectOne("memberMapper.idChk", vo);
		return result;
	}

}

