package allstar.pms.controller.ajax;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import allstar.pms.domain.AjaxResult;
import allstar.pms.domain.Member;
import allstar.pms.service.MemberService;

@Controller("ajax.AuthController")
@RequestMapping("/auth/ajax/*")
public class AuthController { 
  @Autowired MemberService memberService;
  
  @RequestMapping(value="login", method=RequestMethod.GET)
  public String loginform() {
    return "auth/LoginForm";
  }
      
  @RequestMapping(value="login", method=RequestMethod.POST)
  public AjaxResult login(
      String id,
      String password) {
    Member member = memberService.retrieve(id, password);

    if (member == null) { // 로그인 실패!
      return new AjaxResult("failure", null);
    }

    return new AjaxResult("success", member);
  }
  
  @RequestMapping("logout")
  public AjaxResult logout(HttpSession session) {
    session.invalidate();
    return new AjaxResult("success", null);
  }
  
}
