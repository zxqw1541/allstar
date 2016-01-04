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
      String pwd,
      String saveId,
      HttpServletResponse response, 
      HttpSession session) {

    Cookie idCookie = null;
    if (saveId != null) { 
      idCookie = new Cookie("id", id);
      idCookie.setMaxAge(60 * 60 * 24 * 15);
    } else {
      idCookie = new Cookie("id", "");
      idCookie.setMaxAge(0); 
    }
    response.addCookie(idCookie);

    Member member = memberService.retrieve(id, pwd);

    if (member == null) { 
      session.invalidate(); 
      return "auth/LoginFail";
    }

    session.setAttribute("loginUser", member);
    return "redirect:../header.jsp";                      //----------------
  }
  
  @RequestMapping("logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:login.do";
  }
}

