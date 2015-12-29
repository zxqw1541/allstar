package allstar.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam/*")
public class ExamController {
  
  @RequestMapping("list")
  public void list() {
  }

}
