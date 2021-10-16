package com.portfolio.Flea_Market;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portfolio.Flea_Market.SERVICE.BoardService;
import com.portfolio.Flea_Market.SERVICE.MemberService;
import com.portfolio.Flea_Market.VO.BoardVO;
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
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "login";
	}
	
	@RequestMapping(value = "/home")
	public String home() {
		
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
	@RequestMapping("/methodname")
	public String m1(String E) {
		logger.info("tag", E);
		
		return "home";
	}
	@RequestMapping("/writing")
	public String writing() {
		
		return "writing";
	}
	
	/**
	 * 게시판 글쓰기
	 * @param board
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/writingaction")
	public String writingaction(BoardVO boardVo) throws Exception {
//		if(board.getTITLE() == null || board.getCONTENT() == null){
//			PrintWriter script = response.getWriter();
//			script.println("<script>");
//			script.println("alert('입력이 안 된 사항이 있습니다')");
//			script.println("history.back()");
//			script.println("</script>");
//		}else{
			// 정상적으로 입력이 되었다면 글쓰기 로직을 수행한다
//			MemberDAO memberDAO = new MemberDAO();
//			BoardDAO boardDAO = new BoardDAO();
//			int result = boardDAO.write(board.getTITLE(), board.getCONTENT());
//			// 데이터베이스 오류인 경우
//			if(result == -1){
//				PrintWriter script = response.getWriter();
//				script.println("<script>");
//				script.println("alert('글쓰기에 실패했습니다')");
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
		
		logger.debug("게시판 글쓰기 action 중");
		
		boardService.write(boardVo);
		
		return "/"; // 게시판 목록으로
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
//				if(result == -1) { //아이디가 기본키가 , 중복되면 오류
//					
//				}
//				// 가입성공
//				else {
//				
//				}
//			}
		logger.debug("회원가입 하기");
		
		memberService.join(member);
		
		return "login";
	}
	
	@RequestMapping("loginaction")
	public String b(MemberVO member,HttpSession session) {
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
//			// DB 오류
//			else if(result == -2) {
//				return "login";
//			}
		
		MemberVO memberVo;
		String url = "login";
		
		try {
			memberVo = memberService.login(member);
			if (memberVo != null) {
				session.setAttribute("email",member.getEMAIL());
				session.setAttribute("name",member.getNAME());
				session.setAttribute("nickname",member.getNICKNAME());
				session.setAttribute("phonenum",member.getPHONENUMBER());
				url = "home";
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
}