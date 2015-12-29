package allstar.pms.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import allstar.pms.domain.Member;
import allstar.pms.service.MemberService;

@Controller
@RequestMapping("/auth/*")
public class AuthController {  
  @Autowired MemberService memberService;

  @RequestMapping(value="login", method=RequestMethod.GET)
  public String loginform() {
    return "auth/LoginForm";
  }
      
  @RequestMapping(value="login", method=RequestMethod.POST)
  public String login(
      String id,
      String password,
      String saveId,
      HttpServletResponse response, 
      HttpSession session) {

    Cookie idCookie = null;
    if (saveId != null) { // 이메일 저장을 체크했으면,
      idCookie = new Cookie("id", id);
      idCookie.setMaxAge(60 * 60 * 24 * 15);
    } else {
      idCookie = new Cookie("id", "");
      idCookie.setMaxAge(0); // 웹브라우저에게 email 쿠키 삭제를 명령한다.
    }
    response.addCookie(idCookie);

    Member member = memberService.retrieve(id, pwd);

    if (member == null) { // 로그인 실패!
      session.invalidate(); // 세션을 무효화시킴. => 새로 세션 객체 생성!
      return "auth/LoginFail";
    }

    session.setAttribute("loginUser", member);
    return "redirect:../header.jsp";
  }
  
  @RequestMapping("logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:login.do";
  }
}

