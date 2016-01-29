package allstar.pms.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import allstar.pms.domain.JoinComp;
import allstar.pms.domain.Team;
import allstar.pms.service.CompetitionService;
import allstar.pms.service.EventService;
import allstar.pms.service.JoinCompService;
import allstar.pms.service.JoinTeamService;
import allstar.pms.service.TeamService;
import allstar.pms.util.MultipartHelper;
import allstar.pms.util.TournamentHelper;

@Controller
@RequestMapping("/competition/*")
public class CompetitionController {
  
  public static Logger log = Logger.getLogger(CompetitionController.class);
  @Autowired CompetitionService competitionService;
  @Autowired TeamService teamService;
  @Autowired JoinCompService joinCompService;
  @Autowired JoinTeamService joinTeamService;
  @Autowired EventService eventService;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("all")
  public AjaxResult countAll(
      @RequestParam(defaultValue = "null") String event,
      @RequestParam(defaultValue = "null") String addr,
      @RequestParam(defaultValue = "null") String search1,
      @RequestParam(defaultValue = "null") String search2) {
    
    int count = competitionService.countAllCompetition(event, addr, search1, search2);
    
    return new AjaxResult("success",count);
  }

  @RequestMapping("list")
  public AjaxResult list(
      @RequestParam(defaultValue = "1") int pageNo,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "null") String event,
      @RequestParam(defaultValue = "null") String addr,
      @RequestParam(defaultValue = "null") String recruit,
      @RequestParam(defaultValue = "null") String start,
      @RequestParam(defaultValue = "null") String reply,
      @RequestParam(defaultValue = "null") String search1,
      @RequestParam(defaultValue = "null") String search2) {
    
    List<Competition> competitions = competitionService.getCompetitionList(
        pageNo, pageSize, event, addr, recruit, start, reply, search1, search2);
    log.info(competitions);
  
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

    List<Integer> tnoList = joinCompService.getTnoList(competition.getNo());
    List<Team> teamList = new ArrayList<>();
        
    for (int i = 0; i < tnoList.size(); i++) {
      teamList.add(teamService.retrieve(tnoList.get(i)));
    }
    
    /* 대진표 테스트 */
    String oper = TournamentHelper.makeTournament(teamList);
    competition.setOperation(oper);
    log.debug("-------------------------------------------------------");
    log.debug("oper = " + oper);
    log.debug("-------------------------------------------------------");
    /*--------- */
    
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
    
    List<Integer> tnoList = joinCompService.getTnoList(competition.getNo());
    List<Team> teamList = new ArrayList<>();
    
    for (int i = 0; i < tnoList.size(); i++) {
      teamList.add(teamService.retrieve(tnoList.get(i)));
    }
    
    /* 대진표 테스트 */
    String oper = TournamentHelper.makeTournament(teamList);
    competition.setOperation(oper);
    log.debug("-------------------------------------------------------");
    log.debug("oper = " + oper);
    log.debug("-------------------------------------------------------");
    /*--------- */
    
    
    return new AjaxResult("success", competition);
  }
  
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public AjaxResult update(Competition competition, MultipartHttpServletRequest uploadedFile) throws Exception {
    
    /* 필수 데이터 (임시저장) */
    competition.setTno(100);
    /* --------------- */
    if (uploadedFile != null) {
      Iterator<String> itr = uploadedFile.getFileNames();
      if (itr.hasNext()) {
        log.info("--- 파일 업로드 ----");
        MultipartFile mpf = uploadedFile.getFile(itr.next());

        String path = uploadedFile.getServletContext().getRealPath("/");
        String sep = System.getProperty("file.separator");
        String fileName = MultipartHelper.generateFilename(mpf.getOriginalFilename());
        String filePath = path + sep + "competition" + sep + "img" + sep;

        System.out.println(mpf.getOriginalFilename() + " uploaded!");
        try {
          MultipartHelper.generateFile(mpf, filePath + fileName);
          competition.setPoster(fileName);
        } catch (IOException e) {
          e.printStackTrace();
          return new AjaxResult("failure", null);
        }
      }
    }
    
    competition.setOperation("");
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
  
  @RequestMapping(value = "joinc", method = RequestMethod.GET)
  public AjaxResult joinCompetition(JoinComp joinComp){
    log.debug(joinComp);
    if (joinCompService.retrive(joinComp) != 0)
      return new AjaxResult("already", null);
    
    try {
      joinCompService.register(joinComp);
    } catch (Exception e) {
        return new AjaxResult("failure", null);
    }
    return new AjaxResult("success", null);
  }
  
  
  @RequestMapping(value="imglist", method=RequestMethod.GET)
  public AjaxResult getImgPathByCno(int cno) {
    log.debug("*******cno = " + cno);
    List<Integer> tnoList = joinCompService.getTnoList(cno);
    
    if (tnoList.isEmpty())
      return new AjaxResult("empty",null);
    
    List<Team> teamList = new ArrayList<>();
    for (int tno : tnoList) {
      Team addTeam = teamService.getEmblemByTno(tno);
      addTeam.setTno(tno);
      log.debug(addTeam);
      teamList.add(addTeam);
    }
    return new AjaxResult("success", teamList);
  }
  
  @RequestMapping(value="master", method=RequestMethod.GET)
  public AjaxResult getCompetitionMaster(int no) {
    log.debug("no = " + no);
    int a = competitionService.getMnoByCno(no);
    log.debug("a  = " + a);
    return new AjaxResult("success", a);
  }
  
  @RequestMapping(value="availteam", method=RequestMethod.GET)
  public AjaxResult getTeamListByMNno(int mno, int eno) {
    log.debug("mno = " + mno + " eno = " + eno);
    
    return new AjaxResult("success", teamService.getTeamListByTnoEno(mno, eno));
  }
  
  @RequestMapping(value="joincomp", method=RequestMethod.GET)
  public AjaxResult addJoinCompetition(JoinComp joinComp) {
    log.debug("joinComp = " + joinComp);
    log.debug("count : " + joinCompService.retrive(joinComp));
    if (joinCompService.retrive(joinComp) > 0)
      return new AjaxResult("already", null);
    
    joinCompService.register(joinComp);
    
    
    return new AjaxResult("success", null);
  }
  
  @RequestMapping(value="jointeam", method=RequestMethod.GET)
  public AjaxResult listJoinTeam(int mno) {
    log.debug("mno = " + mno);
    return new AjaxResult("success", joinTeamService.getJoinTeamByMember(mno));
  }
  
  @RequestMapping(value="event", method=RequestMethod.GET)
  public AjaxResult listEvent() {
    return new AjaxResult("success", eventService.getEventList());
  }
  
}
 