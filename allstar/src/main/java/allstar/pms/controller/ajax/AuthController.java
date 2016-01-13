package allstar.pms.controller.ajax;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import allstar.pms.dao.MemberDao;
import allstar.pms.domain.AjaxResult;
import allstar.pms.domain.Member;

@Controller("ajax.AuthController")
@RequestMapping("/auth/ajax/*")
public class AuthController { 
  @Autowired MemberDao memberDao;
  
  @RequestMapping(value="login", method=RequestMethod.GET)
  public String loginform() {
    return "auth/LoginForm";
  }
      
  @RequestMapping(value="login", method=RequestMethod.POST)
  public AjaxResult login(
      String id,
      String password,
      HttpServletResponse response, 
      HttpSession session) {

    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("id", id);
    paramMap.put("password", password);
    
    Member member = memberDao.login(paramMap);

    if (member == null) { // 로그인 실패!
      session.invalidate(); // 세션을 무효화시킴. => 새로 세션 객체 생성!
      return new AjaxResult("failure", null);
    }

    session.setAttribute("loginUser", member);
    return new AjaxResult("success", member);
  }
  
  @RequestMapping("logout")
  public AjaxResult logout(HttpSession session) {
    session.invalidate();
    return new AjaxResult("success", null);
  }
  
}
