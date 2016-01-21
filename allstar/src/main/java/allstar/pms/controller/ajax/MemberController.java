package allstar.pms.controller.ajax;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import allstar.pms.domain.AjaxResult;
import allstar.pms.domain.Like_Event;
import allstar.pms.domain.Member;
import allstar.pms.service.Like_EventService;
import allstar.pms.service.MemberService;

@Controller("ajax.MemberController")
@RequestMapping("/member/ajax/*")
public class MemberController {
  public static final String SAVED_DIR = "/attachfile";
  public static Logger log = Logger.getLogger(MemberController.class);
  @Autowired
  MemberService memberService;
  Like_EventService like_eventService;
  @Autowired
  ServletContext servletContext;

  @RequestMapping("list")
  public Object list(
      @RequestParam(defaultValue = "1") int pageNo,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "id") String keyword, 
      @RequestParam(defaultValue = "desc") String align) throws Exception {

    List<Member> members = memberService.getMemberList(pageNo,pageSize,keyword,align);


    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", members);
    return resultMap;
  }



  @RequestMapping(value = "add", method = RequestMethod.POST)
  public AjaxResult add(Member member) throws Exception {

    memberService.register(member);

    return new AjaxResult("success",null);
  }

  @RequestMapping("detail")
  public Object detail(int mno) throws Exception {
    Member member = memberService.retrieve(mno);
    return new AjaxResult("success", member);
  }
  
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public AjaxResult update(Member member) throws Exception {
    
    if (memberService.change(member) <= 0) {
      return new AjaxResult("failure", null);
    }
    return new AjaxResult("success", null);
  }

  @RequestMapping("delete")
  public AjaxResult delete(
      String id, String pwd) throws Exception {
    if (memberService.remove(id, pwd) <= 0) {
      return new AjaxResult("failure", null);
    }
    
    return new AjaxResult("success", null);
  }
  
  @RequestMapping(value="event", method = RequestMethod.POST)
  public AjaxResult likeEvent(
      Like_Event like_event) throws Exception {
    System.out.println(like_event);
    //    like_eventService.remove(like_event.getMno());
    like_eventService.register(like_event);
    
    return new AjaxResult("success", null);
  }

}
