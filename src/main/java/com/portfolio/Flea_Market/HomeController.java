package com.portfolio.Flea_Market;

import java.util.HashMap;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.Flea_Market.SERVICE.BoardService;
import com.portfolio.Flea_Market.SERVICE.CommentService;
import com.portfolio.Flea_Market.SERVICE.MemberService;
import com.portfolio.Flea_Market.SERVICE.ReplyService;
import com.portfolio.Flea_Market.UTIL.EmailUtil;
import com.portfolio.Flea_Market.UTIL.Pagination;
import com.portfolio.Flea_Market.UTIL.PwRandom;
import com.portfolio.Flea_Market.VO.BoardVO;
import com.portfolio.Flea_Market.VO.CommentVO;
import com.portfolio.Flea_Market.VO.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ReplyService replyService;
			
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "login";
	}
	
	@RequestMapping(value = "/home")
	public String home(@ModelAttribute("paramVo") BoardVO vo, Model model, HttpServletRequest request, HttpSession session) throws Exception{
		
		//board paging구현
		Pagination pagination = new Pagination();
		List<BoardVO> tb_boards = null;
		
		try {
			
			//페이징 start
			int totalRecordCount = 0;
			int currentPageNo = vo.getCurrentPageNo();
			int pageSize = 5;
			int recordCountPerPage = vo.getRecordCountPerPage();
			
			//select 박스 검색 보기 갯수
			if (recordCountPerPage == 0) {
				vo.setRecordCountPerPage(1);
			}
			
			// 현재페이지 초기화
			if (currentPageNo == 0) {
				vo.setCurrentPageNo(1);
			}
			
			HashMap<String, Integer> rangeMap = pagination.calcDataRange(currentPageNo, recordCountPerPage);
			vo.setStart(rangeMap.get("firstRecordIndex"));
			vo.setEnd(rangeMap.get("lastRecordIndex"));
			
			//board
			tb_boards = boardService.list(vo); 
			
			if (tb_boards.size() > 0) {
				totalRecordCount = Integer.parseInt(String.valueOf(tb_boards.get(0).getTotalRecordCount()));
			}
			
			HashMap<String, Integer> pagerMap = pagination.calcPagerElement(currentPageNo, totalRecordCount, recordCountPerPage, pageSize);
			
			model.addAttribute("tb_boards", tb_boards);
			model.addAttribute("pagerMap", pagerMap);
			
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		
		return "home";
	}
	@RequestMapping(value = "/join")
	public String join() {
		
		return "join";
	}
	@RequestMapping(value = "/findpassword")
	public String findpassword() {
		
		return "findpassword";
	}
	
	// 비밀번호 찾기 action
	@RequestMapping("/methodname")
	public String m1(MemberVO memberVo, RedirectAttributes rttr, Model model) throws Exception {

		logger.debug("비밀번호 찾기 action~!~!!~");
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String findPwFlag = "C";
		
		try {
			
			MemberVO email = memberService.findEmail(memberVo);
			
			if (email != null) {
				
				//Pw 랜덤
				PwRandom pwRan = new PwRandom();
				//대문자 제거할거면 false, 비밀번호 자리수 선택
				String uptPw = pwRan.getKey(false, 15);

				//Pw 변경
				memberVo.setPASSWORD(uptPw);
				int cnt = memberService.pwUpdate(memberVo);
				
				if (cnt > 0) {
					//이메일 보내기
					EmailUtil emailUtil = new EmailUtil();
					
					emailUtil.mailSend(email.getEMAIL(), uptPw);
					
					findPwFlag = "A";
				} else {
					findPwFlag = "C";
				}
				
			} else {
				//이메일이 없음
				findPwFlag = "B";
			}
			
			
		} catch (Exception e) {
			findPwFlag = "C"; // 오류
			logger.info(e.getMessage());
			e.printStackTrace();
		} finally {
			rttr.addFlashAttribute("findPwFlag", findPwFlag);
			model.addAttribute("findPwFlag", findPwFlag);
		}
		
		return "findpassword";
	}
	@RequestMapping(value="/memberUpdateView", method = RequestMethod.GET)
	public String registerUpdateView(MemberVO vo, Model model, HttpSession session) throws Exception{
		System.out.println("llog");
		System.out.println(session.getAttribute("email"));
		
		vo.setEMAIL((String) session.getAttribute("email"));
		vo.setNAME((String) session.getAttribute("name"));
		
		model.addAttribute("member", vo);
		
		return "/memberUpdateView";
	}

	@RequestMapping(value="/memberUpdate", method = RequestMethod.POST)
	public String registerUpdate(MemberVO vo, HttpSession session) throws Exception{
		
		System.out.println("log");
		System.out.println(vo.getEMAIL());
		memberService.memberUpdate(vo);
		
		session.invalidate();
		
		return "redirect:/";
	}
	// 회원 탈퇴 get
	@RequestMapping(value="/memberDeleteView", method = RequestMethod.GET)
	public String memberDeleteView() throws Exception{
		return "/memberDeleteView";
	}
	
	// 회원 탈퇴 post
	@RequestMapping(value="/memberDelete", method = RequestMethod.POST)
	public String memberDelete(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		// 세션에 있는 member를 가져와 member변수에 넣어줍니다.
		MemberVO member = (MemberVO) session.getAttribute("member");
		// 세션에있는 비밀번호
		String sessionPass = member.getPASSWORD();
		// vo로 들어오는 비밀번호
		String voPass = vo.getPASSWORD();
		
		if(!(sessionPass.equals(voPass))) {
			rttr.addFlashAttribute("msg", false);
			return "redirect:/memberDeleteView";
		}
		memberService.memberDelete(vo);
		session.invalidate();
		return "redirect:/";
	}
	@RequestMapping("/writing")
	public String writing() {
		
		return "writing";
	}
	// 패스워드 체크
		@ResponseBody
		@RequestMapping(value="/passChk", method = RequestMethod.POST)
		public int passChk(MemberVO vo) throws Exception {
			int result = memberService.passChk(vo);
			return result;
		}
		// 아이디 중복 체크
		@ResponseBody
		@RequestMapping(value="/idChk", method = RequestMethod.POST)
		public int idChk(MemberVO vo) throws Exception {
			int result = memberService.idChk(vo);
			return result;
		}

		// 회원가입 post
		@RequestMapping(value = "/register", method = RequestMethod.POST)
		public String postRegister(MemberVO vo) throws Exception {
			logger.info("post register");
			int result = memberService.idChk(vo);
			try {
				if(result == 1) {
					return "/register";
				}else if(result == 0) {
					memberService.join(vo);
				}
				// 요기에서~ 입력된 아이디가 존재한다면 -> 다시 회원가입 페이지로 돌아가기 
				// 존재하지 않는다면 -> register
			} catch (Exception e) {
				throw new RuntimeException();
			}
			return "redirect:/";
		}
	/**
	 * 게시판 글쓰기
	 * @param board
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/writingaction")
	public String writingaction(BoardVO boardVo, HttpServletRequest request) throws Exception {
//		if(board.getTITLE() == null || board.getCONTENT() == null){
//			PrintWriter script = response.getWriter();
//			script.println("<script>");
//			script.println("alert('입력이 안된 사항이 있습니다.')");
//			script.println("history.back()");
//			script.println("</script>");
//		}else{
			// 정상적으로 입력이 되었다면 글쓰기 로직을 수행한다.
//			MemberDAO memberDAO = new MemberDAO();
//			BoardDAO boardDAO = new BoardDAO();
//			int result = boardDAO.write(board.getTITLE(), board.getCONTENT());
//			// 데이터베이스 오류인 경우
//			if(result == -1){
//				PrintWriter script = response.getWriter();
//				script.println("<script>");
//				script.println("alert('글쓰기에 실패했습니다.')");
//				script.println("history.back()");
//				script.println("</script>");
//			// 글쓰기가 정상적으로 실행되면 알림창을 띄우고 게시판 메인으로 이동한다
//			}else {
//				PrintWriter script = response.getWriter();
//				script.println("<script>");
//				script.println("alert('글쓰기 성공')");
//				script.println("location.href='bbs.jsp'");
//				script.println("</script>");
//			}
//		}	
//		return "writingaction";
		
		request.setCharacterEncoding("UTF-8");
		
		logger.debug("게시판 글쓰기 action 중");
		
		boardService.write(boardVo);
		
		return "redirect:/home"; // 게시판 목록으로
	}
	
	@RequestMapping("joinaction")
	public String a(MemberVO member) throws Exception {
//		if (member.getEMAIL() == null || member.getPASSWORD() == null || member.getNAME() == null
//				|| member.getNICKNAME() == null || member.getPHONENUMBER() == null) {
//				
//			} else {
//				MemberDAO memberDAO = new MemberDAO(); // 인스턴스 생성
//				int result = memberDAO.join(member);
//				
//				if(result == -1) { // 아이디가 기본키가, 중복되면 오류
//					
//				}
//				// 가입성공
//				else {
//				
//				}
//			}
		logger.debug("회원가입하기");
		
		memberService.join(member);
		
		return "login";
	}
	
	@RequestMapping("loginaction")
	public String b(MemberVO member, HttpServletRequest request) {
		
//		MemberDAO merberDao = new MemberDAO(); // 인스턴스 생성
//		int result = merberDao.login(member.getEMAIL(),member.getPASSWORD());
//		System.out.println("Result: ");
//		System.out.println(result);
//		
//		// 로그인 성공
//		if(result == 1) {
//			session.setAttribute("email",member.getEMAIL());
//			session.setAttribute("name",member.getNAME());
//			session.setAttribute("nickname",member.getNICKNAME());
//			session.setAttribute("phonenum",member.getPHONENUMBER());
//			return "home";
//			}
//			// 로그인 실패
//			else if(result == 0) {
//				return "login";
//			}
//			// 아이디 없음
//			else if(result == -1) {
//				return "login";
//			}
//			// DB 없음
//			else if(result == -2) {
//				return "login";
//			}
		
		HttpSession session = request.getSession();
		
		
		session.setAttribute("email",member.getEMAIL());
		session.setAttribute("name",member.getNAME());
		session.setAttribute("nickname",member.getNICKNAME());
		session.setAttribute("phonenum",member.getPHONENUMBER());
		MemberVO memberVo;
		String url = "login";
		
		
		
		try {
			memberVo = memberService.login(member);
			if (memberVo != null) {
				
				//세션 유지
				session.setMaxInactiveInterval(30*60);
				
				//객체값으로 저장해야 할듯
				session.setAttribute("sessionMember", memberVo);
				
				url = "redirect:/home";
			}
		} catch (Exception e) {
			url = "login";
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		
		
		return url;
		
	}
	
	@RequestMapping("logoutaction")
	public String c(HttpSession session) {
		session.invalidate();
		return "login";
		
	}
		

	
	// 게시판 자세히 보기
	@RequestMapping(value = "/readView", method = RequestMethod.GET)
	public String read(@ModelAttribute("paramVo") CommentVO vo, Model model, HttpServletRequest request) throws Exception{
		logger.info("readView");
		List<CommentVO> replyList = null;
		MemberVO sessionMember = null;
		
		try {
			
			System.out.println("log");
			System.out.println(request.getSession().getAttribute("sessionMember"));
			//세션값 가져오기 (수정 삭제버튼 보여주기 체크)
			sessionMember = (MemberVO) request.getSession().getAttribute("sessionMember");
			
			//이 number는 게시판 번호 CommentVO number와 다름 (대체하기 위해 씀 문제는 없음)
			model.addAttribute("read", boardService.read(vo.getNUMBER()));

			//Number가 숫자라 문자로 변환함
			//답글 게시판 조회
			vo.setBOARD_NUMBER("" + vo.getNUMBER());
			
			replyList = commentService.selectCommentList(vo);
			
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		
		model.addAttribute("replyList", replyList);
		model.addAttribute("sessionMember", sessionMember);
		
		return "/readView";
	}
	
	// 게시판 수정뷰 ???
	@RequestMapping(value = "/updateView")
	public String updateView(BoardVO boardVO, Model model) throws Exception{
		logger.info("updateView");
		
		model.addAttribute("update", boardService.read(boardVO.getNUMBER()));
		
		return "/updateView";
	}
	
	// 게시판 수정
	@RequestMapping(value = "/update")
	public String update(BoardVO boardVO) throws Exception{
		logger.info("update");
		
		boardService.update(boardVO);
		
		return "redirect:/home";
	}

	// 게시판 삭제
	@RequestMapping(value = "/delete")
	public String delete(BoardVO boardVO) throws Exception{
		logger.info("delete");
		
		boardService.delete(boardVO.getNUMBER());
		
		return "redirect:/home";
	}

	// 게시판 답글 작성저장
	@RequestMapping(value = "/reply")
	public String replyWrite(CommentVO commentVo, HttpSession session, HttpServletRequest request) throws Exception{
		
		logger.info("replyWrite");
		
		MemberVO sessionMember = (MemberVO) request.getSession().getAttribute("sessionMember");
		
		//로그인 한 회원 이름 가져오기
		commentVo.setMASTER_NICKNAME(sessionMember.getNICKNAME());
		commentVo.setUPPER_NUMBER("0");
		commentVo.setMEMBER_NICKNAME(sessionMember.getNICKNAME());
		
		commentService.writeReply(commentVo);
		
		return "redirect:/readView?NUMBER=" + commentVo.getBOARD_NUMBER();
	}
	
	
	//게시판 답글 수정하기
	@RequestMapping(value = "/replyUpdate")
	public String replyUpdate(CommentVO commentVo, HttpSession session, HttpServletRequest request) throws Exception{
		
		System.out.println("게시판 넘버 " + commentVo.getBOARD_NUMBER());
		System.out.println("답글 넘버  " + commentVo.getNUMBER());

		commentVo.setCONTENT(commentVo.getCONTENT().replace(",", " "));
		
		commentService.updateReply(commentVo);
		
		return "redirect:/readView?NUMBER=" + commentVo.getBOARD_NUMBER();
	}
	
	//게시판 답글 삭제하기
	@RequestMapping(value = "/replyDelete")
	public String replyDelete(CommentVO commentVo, HttpSession session, HttpServletRequest request) throws Exception{
		
		System.out.println("게시판 넘버 " + commentVo.getBOARD_NUMBER());
		System.out.println("답글 넘버  " + commentVo.getNUMBER());
		
		commentService.deleteReply(commentVo);
		
		return "redirect:/readView?NUMBER=" + commentVo.getBOARD_NUMBER();
	}
	
}
