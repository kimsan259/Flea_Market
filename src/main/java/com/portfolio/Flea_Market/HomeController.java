package com.portfolio.Flea_Market;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portfolio.Flea_Market.DAO.MemberDAO;
import com.portfolio.Flea_Market.VO.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
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
	@RequestMapping("/writingaction")
	public String writingaction() {
		
		return "writingaction";
	}
	@RequestMapping("joinaction")
	public String a(MemberVO member ) {
		if (member.getEMAIL() == null || member.getPASSWORD() == null || member.getNAME() == null
				|| member.getNICKNAME() == null || member.getPHONENUMBER() == null) {
				
			} else {
				MemberDAO memberDAO = new MemberDAO(); // 인스턴스 생성
				int result = memberDAO.join(member);
				
				if(result == -1) { //아이디가 기본키가 , 중복되면 오류
					
				}
				// 가입성공
				else {
				
				}
			}
		return "login";
	}
	@RequestMapping("loginaction")
	public String b(MemberVO member,HttpSession session) {
		MemberDAO merberDao = new MemberDAO(); // 인스턴스 생성
		int result = merberDao.login(member.getEMAIL(),member.getPASSWORD());
		System.out.println("Result: ");
		System.out.println(result);
		
		// 로그인 성공
		if(result == 1) {
			session.setAttribute("email",member.getEMAIL());
			session.setAttribute("name",member.getNAME());
			session.setAttribute("nickname",member.getNICKNAME());
			session.setAttribute("phonenum",member.getPHONENUMBER());
			return "home";
			}
			// 로그인 실패
			else if(result == 0) {
				return "login";
			}
			// 아이디 없음
			else if(result == -1) {
				return "login";
			}
			// DB 오류
			else if(result == -2) {
				return "login";
			}
		return "login";
		
	}
	@RequestMapping("logoutaction")
	public String c(HttpSession session) {
		session.invalidate();
		return "login";
		
	}
}
