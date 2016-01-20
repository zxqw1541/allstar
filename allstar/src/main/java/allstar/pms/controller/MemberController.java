package allstar.pms.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import allstar.pms.domain.AjaxResult;
import allstar.pms.domain.Member;
import allstar.pms.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
  public static Logger log = Logger.getLogger(MemberController.class);
  @Autowired MemberService memberService;

  @RequestMapping("list")
  public String list(
      @RequestParam(defaultValue = "1") int pageNo,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "id") String keyword, 
      @RequestParam(defaultValue = "desc") String align,
      Model model) {
    
    List<Member> members = memberService.getMemberList(
        pageNo, pageSize, keyword, align);
    log.info(members);
    model.addAttribute("members", members);
    return "member/MemberList";
  }
  
  @RequestMapping(value = "add", method = RequestMethod.GET)
  public String form() {
    return "member/MemberForm";
  }
  
  @RequestMapping(value = "add", method = RequestMethod.POST)
  public String add(Member member) {
    memberService.register(member);    
    return "redirect:list.do";
  }
  
  @RequestMapping(value = "detail", method = RequestMethod.GET)
  public String detail(int mno, Model model) {
    Member member = memberService.retrieve(mno);
    model.addAttribute("member", member);
    return "member/MemberDetail";
  }
  
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public String update(Member member) {
    memberService.change(member);
    return "redirect:list.do";
  }
  
  @RequestMapping(value = "delete", method = RequestMethod.GET)
  public String delete(String id, String pwd) {
    memberService.remove(id, pwd);
    return "redirect:list.do";
  }
  

}
