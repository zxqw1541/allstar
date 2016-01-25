package allstar.pms.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import allstar.pms.domain.AjaxResult;
import allstar.pms.domain.Competition;
import allstar.pms.service.CompetitionService;
import allstar.pms.util.MultipartHelper;

@Controller
@RequestMapping("/competition/*")
public class CompetitionController {
  
  public static Logger log = Logger.getLogger(CompetitionController.class);
  @Autowired CompetitionService competitionService;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("all")
  public AjaxResult countAll(
      @RequestParam(defaultValue = "null") String search1,
      @RequestParam(defaultValue = "null") String search2) {
    
    int count = competitionService.countAllCompetition(search1, search2);
    
    return new AjaxResult("success",count);
  }

  @RequestMapping("list")
  public AjaxResult list(
      @RequestParam(defaultValue = "1") int pageNo,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "id") String keyword, 
      @RequestParam(defaultValue = "desc") String align,
      @RequestParam(defaultValue = "null") String search1,
      @RequestParam(defaultValue = "null") String search2) {
    
    List<Competition> competitions = competitionService.getCompetitionList(
        pageNo, pageSize, keyword, align, search1, search2);
    log.info(competitions);
    System.out.println(keyword);
    
    return new AjaxResult("success", competitions);
  }
  
  @RequestMapping(value = "add", method = RequestMethod.GET)
  public String form() {
    return "competition/CompetitionForm";
  }
  
  @RequestMapping(value = "add", method = RequestMethod.POST)
  public AjaxResult add(Competition competition, MultipartHttpServletRequest uploadedFile) throws Exception {
    log.info("competition = " + competition);
    log.info("file = " + uploadedFile);
    /* 필수 데이터 (임시저장) */
    competition.setPoster("1");
    competition.setTno(1);
    /* 나중에 입력 받아오면 지울 것 */
    
    Iterator<String> itr =  uploadedFile.getFileNames();
    if(itr.hasNext()) {
      MultipartFile mpf = uploadedFile.getFile(itr.next());
      
      String path=uploadedFile.getServletContext().getRealPath("/");
      String sep = System.getProperty("file.separator");
      String fileName = MultipartHelper.generateFilename(mpf.getOriginalFilename());
      String filePath = path+ sep + "competition" + sep + "img" + sep;
      
      System.out.println(mpf.getOriginalFilename() +" uploaded!");
      try {
        MultipartHelper.generateFile(mpf, filePath+fileName);
        competition.setPoster(fileName);
      } catch (IOException e) {
        e.printStackTrace();
        return new AjaxResult("failure", null);
      }
    }
    
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
 