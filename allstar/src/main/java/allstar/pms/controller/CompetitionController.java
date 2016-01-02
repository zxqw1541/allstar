package allstar.pms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import allstar.pms.domain.Competition;
import allstar.pms.service.CompetitionService;

@Controller
@RequestMapping("/competition/*")
public class CompetitionController {
  public static Logger log = Logger.getLogger(CompetitionController.class);
  @Autowired CompetitionService competitionService;

  @RequestMapping("list")
  public String list(
      @RequestParam(defaultValue = "1") int pageNo,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "id") String keyword, 
      @RequestParam(defaultValue = "desc") String align,
      Model model) {
    
    List<Competition> competitions = competitionService.getCompetitionList(
        pageNo, pageSize, keyword, align);
    log.info(competitions);
    model.addAttribute("competitions", competitions);
    return "competition/CompetitionList";
  }
  
  @RequestMapping(value = "add", method = RequestMethod.GET)
  public String form() {
    return "competition/CompetitionForm";
  }
  
  @RequestMapping(value = "add", method = RequestMethod.POST)
  public String add(Competition competition) {
    competitionService.register(competition);    
    return "redirect:list.do";
  }
  
  @RequestMapping(value = "detail", method = RequestMethod.GET)
  public String detail(int no, Model model) {
    Competition competition = competitionService.retrieve(no);
    model.addAttribute("competition", competition);
    return "competition/CompetitionDetail";
  }
  
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public String update(Competition competition) {
    competitionService.change(competition);
    return "redirect:list.do";
  }
  
  @RequestMapping(value = "delete", method = RequestMethod.GET)
  public String delete(int no) {
    competitionService.remove(no);
    return "redirect:list.do";
  }
  
  
  
}
