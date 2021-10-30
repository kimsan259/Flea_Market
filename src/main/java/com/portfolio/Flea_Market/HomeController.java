package com.portfolio.Flea_Market;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
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
	public String home(Model model, HttpServletRequest request, HttpSession session) throws Exception{
		
		//board
		List<BoardVO> tb_boards = boardService.list(); 
		
		model.addAttribute("tb_boards", tb_boards);
		
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
	 * 寃뚯떆�뙋 湲��벐湲�
	 * @param board
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/writingaction")
	public String writingaction(BoardVO boardVo, HttpServletRequest request) throws Exception {
//		if(board.getTITLE() == null || board.getCONTENT() == null){
//			PrintWriter script = response.getWriter();
//			script.println("<script>");
//			script.println("alert('�엯�젰�씠 �븞 �맂 �궗�빆�씠 �엳�뒿�땲�떎')");
//			script.println("history.back()");
//			script.println("</script>");
//		}else{
			// �젙�긽�쟻�쑝濡� �엯�젰�씠 �릺�뿀�떎硫� 湲��벐湲� 濡쒖쭅�쓣 �닔�뻾�븳�떎
//			MemberDAO memberDAO = new MemberDAO();
//			BoardDAO boardDAO = new BoardDAO();
//			int result = boardDAO.write(board.getTITLE(), board.getCONTENT());
//			// �뜲�씠�꽣踰좎씠�뒪 �삤瑜섏씤 寃쎌슦
//			if(result == -1){
//				PrintWriter script = response.getWriter();
//				script.println("<script>");
//				script.println("alert('湲��벐湲곗뿉 �떎�뙣�뻽�뒿�땲�떎')");
//				script.println("history.back()");
//				script.println("</script>");
//			// 湲��벐湲곌� �젙�긽�쟻�쑝濡� �떎�뻾�릺硫� �븣由쇱갹�쓣 �쓣�슦怨� 寃뚯떆�뙋 硫붿씤�쑝濡� �씠�룞�븳�떎
//			}else {
//				PrintWriter script = response.getWriter();
//				script.println("<script>");
//				script.println("alert('湲��벐湲� �꽦怨�')");
//				script.println("location.href='bbs.jsp'");
//				script.println("</script>");
//			}
//		}	
//		return "writingaction";
		
		request.setCharacterEncoding("UTF-8");
		
		logger.debug("寃뚯떆�뙋 湲��벐湲� action 以�");
		
		boardService.write(boardVo);
		
		return "redirect:/home"; // 寃뚯떆�뙋 紐⑸줉�쑝濡�
	}
	
	@RequestMapping("joinaction")
	public String a(MemberVO member) throws Exception {
//		if (member.getEMAIL() == null || member.getPASSWORD() == null || member.getNAME() == null
//				|| member.getNICKNAME() == null || member.getPHONENUMBER() == null) {
//				
//			} else {
//				MemberDAO memberDAO = new MemberDAO(); // �씤�뒪�꽩�뒪 �깮�꽦
//				int result = memberDAO.join(member);
//				
//				if(result == -1) { //�븘�씠�뵒媛� 湲곕낯�궎媛� , 以묐났�릺硫� �삤瑜�
//					
//				}
//				// 媛��엯�꽦怨�
//				else {
//				
//				}
//			}
		logger.debug("�쉶�썝媛��엯 �븯湲�");
		
		memberService.join(member);
		
		return "login";
	}
	
	@RequestMapping("loginaction")
	public String b(MemberVO member,HttpSession session) {
//		MemberDAO merberDao = new MemberDAO(); // �씤�뒪�꽩�뒪 �깮�꽦
//		int result = merberDao.login(member.getEMAIL(),member.getPASSWORD());
//		System.out.println("Result: ");
//		System.out.println(result);
//		
//		// 濡쒓렇�씤 �꽦怨�
//		if(result == 1) {
//			session.setAttribute("email",member.getEMAIL());
//			session.setAttribute("name",member.getNAME());
//			session.setAttribute("nickname",member.getNICKNAME());
//			session.setAttribute("phonenum",member.getPHONENUMBER());
//			return "home";
//			}
//			// 濡쒓렇�씤 �떎�뙣
//			else if(result == 0) {
//				return "login";
//			}
//			// �븘�씠�뵒 �뾾�쓬
//			else if(result == -1) {
//				return "login";
//			}
//			// DB �삤瑜�
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
		
		
	// 寃뚯떆�뙋 紐⑸줉 議고쉶
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) throws Exception{
		logger.info("list");
			
		model.addAttribute("list",boardService.list());
		
		
		return "board/list";
		
	}
	
	// 寃뚯떆�뙋 議고쉶
	@RequestMapping(value = "/readView", method = RequestMethod.GET)
	public String read(BoardVO boardVO, Model model) throws Exception{
		logger.info("read");
		
		model.addAttribute("read", boardService.read(boardVO.getNUMBER()));
		
		return "/readView";
	}
		
	
}
