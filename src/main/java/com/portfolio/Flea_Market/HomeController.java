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
		
		//board paging??????
		Pagination pagination = new Pagination();
		List<BoardVO> tb_boards = null;
		
		try {
			
			//????????? start
			int totalRecordCount = 0;
			int currentPageNo = vo.getCurrentPageNo();
			int pageSize = 5;
			int recordCountPerPage = vo.getRecordCountPerPage();
			
			//select ?????? ?????? ?????? ??????
			if (recordCountPerPage == 0) {
				vo.setRecordCountPerPage(1);
			}
			
			// ??????????????? ?????????
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
	
	// ???????????? ?????? action
	@RequestMapping("/methodname")
	public String m1(MemberVO memberVo, RedirectAttributes rttr, Model model) throws Exception {

		logger.debug("???????????? ?????? action~!~!!~");
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String findPwFlag = "C";
		
		try {
			
			MemberVO email = memberService.findEmail(memberVo);
			
			if (email != null) {
				
				//Pw ??????
				PwRandom pwRan = new PwRandom();
				//????????? ??????????????? false, ???????????? ????????? ??????
				String uptPw = pwRan.getKey(false, 15);

				//Pw ??????
				memberVo.setPASSWORD(uptPw);
				int cnt = memberService.pwUpdate(memberVo);
				
				if (cnt > 0) {
					//????????? ?????????
					EmailUtil emailUtil = new EmailUtil();
					
					emailUtil.mailSend(email.getEMAIL(), uptPw);
					
					findPwFlag = "A";
				} else {
					findPwFlag = "C";
				}
				
			} else {
				//???????????? ??????
				findPwFlag = "B";
			}
			
			
		} catch (Exception e) {
			findPwFlag = "C"; // ??????
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
	// ?????? ?????? get
	@RequestMapping(value="/memberDeleteView", method = RequestMethod.GET)
	public String memberDeleteView() throws Exception{
		return "/memberDeleteView";
	}
	
	// ?????? ?????? post
	@RequestMapping(value="/memberDelete", method = RequestMethod.POST)
	public String memberDelete(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		// ????????? ?????? member??? ????????? member????????? ???????????????.
		MemberVO member = (MemberVO) session.getAttribute("member");
		// ??????????????? ????????????
		String sessionPass = member.getPASSWORD();
		// vo??? ???????????? ????????????
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
	// ???????????? ??????
		@ResponseBody
		@RequestMapping(value="/passChk", method = RequestMethod.POST)
		public int passChk(MemberVO vo) throws Exception {
			int result = memberService.passChk(vo);
			return result;
		}
		// ????????? ?????? ??????
		@ResponseBody
		@RequestMapping(value="/idChk", method = RequestMethod.POST)
		public int idChk(MemberVO vo) throws Exception {
			int result = memberService.idChk(vo);
			return result;
		}

		// ???????????? post
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
				// ????????????~ ????????? ???????????? ??????????????? -> ?????? ???????????? ???????????? ???????????? 
				// ???????????? ???????????? -> register
			} catch (Exception e) {
				throw new RuntimeException();
			}
			return "redirect:/";
		}
	/**
	 * ????????? ?????????
	 * @param board
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/writingaction")
	public String writingaction(BoardVO boardVo, HttpServletRequest request) throws Exception {
//		if(board.getTITLE() == null || board.getCONTENT() == null){
//			PrintWriter script = response.getWriter();
//			script.println("<script>");
//			script.println("alert('????????? ?????? ????????? ????????????.')");
//			script.println("history.back()");
//			script.println("</script>");
//		}else{
			// ??????????????? ????????? ???????????? ????????? ????????? ????????????.
//			MemberDAO memberDAO = new MemberDAO();
//			BoardDAO boardDAO = new BoardDAO();
//			int result = boardDAO.write(board.getTITLE(), board.getCONTENT());
//			// ?????????????????? ????????? ??????
//			if(result == -1){
//				PrintWriter script = response.getWriter();
//				script.println("<script>");
//				script.println("alert('???????????? ??????????????????.')");
//				script.println("history.back()");
//				script.println("</script>");
//			// ???????????? ??????????????? ???????????? ???????????? ????????? ????????? ???????????? ????????????
//			}else {
//				PrintWriter script = response.getWriter();
//				script.println("<script>");
//				script.println("alert('????????? ??????')");
//				script.println("location.href='bbs.jsp'");
//				script.println("</script>");
//			}
//		}	
//		return "writingaction";
		
		request.setCharacterEncoding("UTF-8");
		
		logger.debug("????????? ????????? action ???");
		
		boardService.write(boardVo);
		
		return "redirect:/home"; // ????????? ????????????
	}
	
	@RequestMapping("joinaction")
	public String a(MemberVO member) throws Exception {
//		if (member.getEMAIL() == null || member.getPASSWORD() == null || member.getNAME() == null
//				|| member.getNICKNAME() == null || member.getPHONENUMBER() == null) {
//				
//			} else {
//				MemberDAO memberDAO = new MemberDAO(); // ???????????? ??????
//				int result = memberDAO.join(member);
//				
//				if(result == -1) { // ???????????? ????????????, ???????????? ??????
//					
//				}
//				// ????????????
//				else {
//				
//				}
//			}
		logger.debug("??????????????????");
		
		memberService.join(member);
		
		return "login";
	}
	
	@RequestMapping("loginaction")
	public String b(MemberVO member, HttpServletRequest request) {
		
//		MemberDAO merberDao = new MemberDAO(); // ???????????? ??????
//		int result = merberDao.login(member.getEMAIL(),member.getPASSWORD());
//		System.out.println("Result: ");
//		System.out.println(result);
//		
//		// ????????? ??????
//		if(result == 1) {
//			session.setAttribute("email",member.getEMAIL());
//			session.setAttribute("name",member.getNAME());
//			session.setAttribute("nickname",member.getNICKNAME());
//			session.setAttribute("phonenum",member.getPHONENUMBER());
//			return "home";
//			}
//			// ????????? ??????
//			else if(result == 0) {
//				return "login";
//			}
//			// ????????? ??????
//			else if(result == -1) {
//				return "login";
//			}
//			// DB ??????
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
				
				//?????? ??????
				session.setMaxInactiveInterval(30*60);
				
				//??????????????? ???????????? ??????
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
		

	
	// ????????? ????????? ??????
	@RequestMapping(value = "/readView", method = RequestMethod.GET)
	public String read(@ModelAttribute("paramVo") CommentVO vo, Model model, HttpServletRequest request) throws Exception{
		logger.info("readView");
		List<CommentVO> replyList = null;
		MemberVO sessionMember = null;
		
		try {
			
			System.out.println("log");
			System.out.println(request.getSession().getAttribute("sessionMember"));
			//????????? ???????????? (?????? ???????????? ???????????? ??????)
			sessionMember = (MemberVO) request.getSession().getAttribute("sessionMember");
			
			//??? number??? ????????? ?????? CommentVO number??? ?????? (???????????? ?????? ??? ????????? ??????)
			model.addAttribute("read", boardService.read(vo.getNUMBER()));

			//Number??? ????????? ????????? ?????????
			//?????? ????????? ??????
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
	
	// ????????? ????????? ???
	@RequestMapping(value = "/updateView")
	public String updateView(BoardVO boardVO, Model model) throws Exception{
		logger.info("updateView");
		
		model.addAttribute("update", boardService.read(boardVO.getNUMBER()));
		
		return "/updateView";
	}
	
	// ????????? ??????
	@RequestMapping(value = "/update")
	public String update(BoardVO boardVO) throws Exception{
		logger.info("update");
		
		boardService.update(boardVO);
		
		return "redirect:/home";
	}

	// ????????? ??????
	@RequestMapping(value = "/delete")
	public String delete(BoardVO boardVO) throws Exception{
		logger.info("delete");
		
		boardService.delete(boardVO.getNUMBER());
		
		return "redirect:/home";
	}

	// ????????? ?????? ????????????
	@RequestMapping(value = "/reply")
	public String replyWrite(CommentVO commentVo, HttpSession session, HttpServletRequest request) throws Exception{
		
		logger.info("replyWrite");
		
		MemberVO sessionMember = (MemberVO) request.getSession().getAttribute("sessionMember");
		
		//????????? ??? ?????? ?????? ????????????
		commentVo.setMASTER_NICKNAME(sessionMember.getNICKNAME());
		commentVo.setUPPER_NUMBER("0");
		commentVo.setMEMBER_NICKNAME(sessionMember.getNICKNAME());
		
		commentService.writeReply(commentVo);
		
		return "redirect:/readView?NUMBER=" + commentVo.getBOARD_NUMBER();
	}
	
	
	//????????? ?????? ????????????
	@RequestMapping(value = "/replyUpdate")
	public String replyUpdate(CommentVO commentVo, HttpSession session, HttpServletRequest request) throws Exception{
		
		System.out.println("????????? ?????? " + commentVo.getBOARD_NUMBER());
		System.out.println("?????? ??????  " + commentVo.getNUMBER());

		commentVo.setCONTENT(commentVo.getCONTENT().replace(",", " "));
		
		commentService.updateReply(commentVo);
		
		return "redirect:/readView?NUMBER=" + commentVo.getBOARD_NUMBER();
	}
	
	//????????? ?????? ????????????
	@RequestMapping(value = "/replyDelete")
	public String replyDelete(CommentVO commentVo, HttpSession session, HttpServletRequest request) throws Exception{
		
		System.out.println("????????? ?????? " + commentVo.getBOARD_NUMBER());
		System.out.println("?????? ??????  " + commentVo.getNUMBER());
		
		commentService.deleteReply(commentVo);
		
		return "redirect:/readView?NUMBER=" + commentVo.getBOARD_NUMBER();
	}
	
}
