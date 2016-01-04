package allstar.pms.controller.ajax;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import allstar.pms.domain.AjaxResult;
import allstar.pms.domain.Competition;
import allstar.pms.service.CompetitionService;

@Controller("ajax.CompetitionController")
@RequestMapping("/competition/ajax/*")
public class CompetitionController {
  
  public static Logger log = Logger.getLogger(CompetitionController.class);
  @Autowired CompetitionService competitionService;
  @Autowired ServletContext servletContext;

  @RequestMapping("list")
  public AjaxResult list(
      @RequestParam(defaultValue = "1") int pageNo,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "id") String keyword, 
      @RequestParam(defaultValue = "desc") String align) {
    
    List<Competition> competitions = competitionService.getCompetitionList(
        pageNo, pageSize, keyword, align);
    
    return new AjaxResult("success", competitions);
  }
  
  @RequestMapping(value = "add", method = RequestMethod.GET)
  public String form() {
    return "competition/CompetitionForm";
  }
  
  @RequestMapping(value = "add", method = RequestMethod.POST)
  public AjaxResult add(Competition competition) {
    log.info("ajax/add");
    competitionService.register(competition);    
    return new AjaxResult("success", null);
  }
  
  @RequestMapping(value = "detail", method = RequestMethod.GET)
  public AjaxResult detail(int no) {
    Competition competition = competitionService.retrieve(no);
    return new AjaxResult("success", competition);
  }
  
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public AjaxResult update(Competition competition) {
    if (competitionService.change(competition) <= 0)
      return new AjaxResult("failure", null);
    return new AjaxResult("success", null);
  }
  
  @RequestMapping(value = "delete", method = RequestMethod.GET)
  public AjaxResult delete(int no) {
    if (competitionService.remove(no) <= 0)
      return  new AjaxResult("failure", null);
    return new AjaxResult("success", null);
  }
}
 