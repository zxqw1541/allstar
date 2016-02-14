package allstar.pms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import allstar.pms.domain.CompComm;
import allstar.pms.domain.Competition;
import allstar.pms.domain.JoinComp;
import allstar.pms.domain.Team;
import allstar.pms.service.CompCommService;
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
  @Autowired CompCommService compCommService;
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
    
    List<Integer> numberOfComms = new ArrayList<>();
    for(Competition c : competitions) {
      numberOfComms.add(compCommService.countAllCommFromComp(c.getNo()));
    }
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("comp", competitions);
    resultMap.put("countComm", numberOfComms);
  
    return new AjaxResult("success", resultMap);
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
    
    // 대회 등록
    competitionService.register(competition);
    
    // 등록한 팀 대회 참가하기
    JoinComp jc = new JoinComp();
    jc.setCno(competitionService.getLastAddCno());
    jc.setTno(competition.getTno());
    jc.setContent("대회 개최팀");
    jc.setState(1);
    joinCompetition(jc);
    competitionService.plus1JoinNum(competitionService.getLastAddCno());
    
    return new AjaxResult("success", null);
  }
  
  @RequestMapping(value = "detail", method = RequestMethod.GET)
  public AjaxResult detail(int no) {
    Competition competition = competitionService.retrieve(no);
    System.out.println(competition);
    return new AjaxResult("success", competition);
  }
  
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public AjaxResult update(Competition competition, MultipartHttpServletRequest uploadedFile) throws Exception {
    
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
    
    //competition.setOperation("");
    if (competitionService.change(competition) <= 0)
      return new AjaxResult("failure", null);
    return new AjaxResult("success", null);
  }
  
  @RequestMapping(value = "updateTour", method = RequestMethod.POST)
  public AjaxResult updateTour(int no, String operation) throws Exception {
    System.out.println("operation => " + operation);
    if (competitionService.changeTournament(no, operation) <= 0)
      return new AjaxResult("failure", null);
    return new AjaxResult("success", null);
  }
  
  @RequestMapping(value = "delete", method = RequestMethod.GET)
  public AjaxResult delete(int no) {
    if (competitionService.remove(no) <= 0)
      return  new AjaxResult("failure", null);
    return new AjaxResult("success", null);
  }
  
  @RequestMapping(value="joincomp", method=RequestMethod.GET)
  public AjaxResult joinCompetition(JoinComp joinComp) {
    log.debug(joinComp);
    // 이미 등록되어있는지
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
    List<Integer> tnoList = joinCompService.getApprovedTnoList(cno);
    
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
  
  @RequestMapping(value="jointeam", method=RequestMethod.GET)
  public AjaxResult getListJoinTeam(int mno) {
    log.debug("mno = " + mno);
    System.out.println(joinTeamService.getOpenTeamByMember(mno));
    return new AjaxResult("success", joinTeamService.getOpenTeamByMember(mno));
  }
  
  @RequestMapping(value="event", method=RequestMethod.GET)
  public AjaxResult getListEvent() {
    return new AjaxResult("success", eventService.getEventList());
  }
  
  @RequestMapping(value="commlist", method=RequestMethod.GET)
  public AjaxResult getCompCommList(int cno){
    log.debug(cno);
    
    return new AjaxResult("success", compCommService.getCompCommListByComp(cno));
  }
  
  @RequestMapping(value="addcomm", method=RequestMethod.POST)
  public AjaxResult addCompComm(CompComm comm){
    log.debug(comm);
    compCommService.register(comm);
    return new AjaxResult("success", compCommService.getLastCommByComp(comm.getCno()));
  }
  
  /* 해당 맴버(개설한 팀들)가 주최한 대회 리스트 */
  @RequestMapping(value="complistm", method=RequestMethod.GET)
  public AjaxResult getCompListByMno(int mno){
    log.debug(mno);
    log.debug(competitionService.getCompListByMno(mno));
    
    return new AjaxResult("success", competitionService.getCompListByMno(mno));
  }
  
  @RequestMapping(value="jclist", method=RequestMethod.GET)
  public AjaxResult getJoinTeamListByCno(int cno) {
    
    return new AjaxResult("success",joinCompService.getJoinListByCno(cno));
  }
  
  @RequestMapping(value="changeState", method=RequestMethod.POST)
  public AjaxResult changeStateJoinComp(int cno, int tno, int state) {
    int preState = joinCompService.getState(tno, cno);
        
    log.debug("--------상태-------------");
    log.debug(preState);

    //승인 눌렀을 때 
    if (state == 1) {
      // 원래 승인상태였으면 패스
      if (preState == 1)
        return new AjaxResult("success", null);
      
      // 꽉찼을때
      Competition c = competitionService.getJoinNTeamNum(cno);
      if (c.getJoinNum() >= c.getTeamNum()) {
        return new AjaxResult("full", null);
      }
      // joinNum 증가
      joinCompService.changeState(tno, cno, state);
      competitionService.plus1JoinNum(cno);
      
      // 대진표 재작성
      List<Integer> tnoList = joinCompService.getTnoList(cno);
      List<Team> teamList = new ArrayList<>();
          
      for (int i = 0; i < tnoList.size(); i++) {
        teamList.add(teamService.retrieve(tnoList.get(i)));
      }
      
      Competition competition = competitionService.retrieve(cno);
      
      String oper = TournamentHelper.makeTournament(teamList);
      competition.setOperation(oper);
      log.debug("-------------------------------------------------------");
      log.debug("oper = " + oper);
      log.debug("-------------------------------------------------------");
      /*--------- */
      competitionService.change(competition);
      return new AjaxResult("success", null);
    }
    
    
    // 승인이 아닐때
    joinCompService.changeState(tno, cno, state);
    
    // 승인-> 취소할 때 현재인원 내리고 대진표 다시작성 
    if (preState == 1) {
      competitionService.minus1JoinNum(cno);
      
      // 대진표 재작성
      List<Integer> tnoList = joinCompService.getTnoList(cno);
      List<Team> teamList = new ArrayList<>();
          
      for (int i = 0; i < tnoList.size(); i++) {
        teamList.add(teamService.retrieve(tnoList.get(i)));
      }
      
      Competition competition = competitionService.retrieve(cno);
      
      String oper = TournamentHelper.makeTournament(teamList);
      competition.setOperation(oper);
      log.debug("-------------------------------------------------------");
      log.debug("oper = " + oper);
      log.debug("-------------------------------------------------------");
      /*--------- */
      competitionService.change(competition);
    }
      
    return new AjaxResult("success", null);
  }
  
  /* 해당 맴버(개설한 팀들)가 참여한 대회 리스트 */
  @RequestMapping(value="compJoined", method=RequestMethod.GET)
  public AjaxResult getJoinedCompListByMno(int mno){
    log.debug(mno);

    return new AjaxResult("success", joinCompService.getJoinedCompList(mno));
  }
  
  @RequestMapping(value="sendResult", method=RequestMethod.POST)
  public AjaxResult updateTeamScoreByMno(
      @RequestParam(defaultValue = "null") String winTeam,
      @RequestParam(defaultValue = "null") String loseTeam,
      @RequestParam(defaultValue = "null") String name) {
    
    String[] winTeamList;
    String[] loseTeamList;
    winTeamList = winTeam.split(",");
    loseTeamList = loseTeam.split(",");
    
    System.out.println("win =====================> " + winTeam);
    for (int i = 0; i < winTeamList.length; i++) {
      int win = 1, lose = 0;
      if (winTeamList[i].startsWith("undefined")) {
        continue;
      }
      if (teamService.changeTeamScore(win, lose, winTeamList[i]) <= 0) {
        return new AjaxResult("fail", null);
      }
    }
    System.out.println("lose =====================> " + loseTeam);
    for (int i = 0; i < loseTeamList.length; i++) {
      int win = 0, lose = 1;
      if (loseTeamList[i].startsWith("undefined")) {
        continue;
      }
      if (teamService.changeTeamScore(win, lose, loseTeamList[i]) <= 0) {
        return new AjaxResult("fail", null);
      }
    }
    
    return new AjaxResult("success", null);
  }
}
 