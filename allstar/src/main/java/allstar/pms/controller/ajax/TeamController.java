package allstar.pms.controller.ajax;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import allstar.pms.domain.AjaxResult;
import allstar.pms.domain.Event;
import allstar.pms.domain.JoinTeam;
import allstar.pms.domain.Team;
import allstar.pms.service.EventService;
import allstar.pms.service.JoinCompService;
import allstar.pms.service.JoinTeamService;
import allstar.pms.service.TeamService;
import allstar.pms.util.MultipartHelper;

@Controller("ajax.TeamController")
@RequestMapping("/team/ajax/*")
public class TeamController {
  static public Logger log = Logger.getLogger(TeamController.class);

  @Autowired TeamService teamService;
  @Autowired EventService eventService;
  @Autowired JoinTeamService joinTeamService;
  @Autowired JoinCompService joinCompService;
  
  @RequestMapping("all")
  public Object listAll() throws Exception {
    
    List<Team> teams = teamService.getList();
    
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("size", teams.size());

    return resultMap;
  }

  @RequestMapping("count")
  public Object listCount(
      @RequestParam(defaultValue="null") String event,
      @RequestParam(defaultValue="null") String addr,
      @RequestParam(defaultValue="null") String possible,
      @RequestParam(defaultValue="null") String play,
      @RequestParam(defaultValue="null") String enroll) throws Exception {
    
    List<Team> teams = teamService.getCount(event, addr, possible, play, enroll);
    
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("count", teams.size());
    
    return resultMap;
  }

  @RequestMapping("list")
  public Object list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="10") int pageSize,
      @RequestParam(defaultValue="null") String event,
      @RequestParam(defaultValue="null") String addr,
      @RequestParam(defaultValue="null") String possible,
      @RequestParam(defaultValue="null") String play,
      @RequestParam(defaultValue="null") String enroll) throws Exception {

    List<Team> teams = teamService.getTeamList(
        pageNo, pageSize, event, addr, possible, play, enroll);
    List<Event> events = eventService.getEventList();
    
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("teams", teams);
    resultMap.put("size", teams.size());
    resultMap.put("events", events);

    return resultMap;
  }
  
  @RequestMapping(value="add", method=RequestMethod.POST)
  public AjaxResult add(Team team, String mno, MultipartHttpServletRequest uploadedFile) throws Exception {
    
    System.out.println("mno ==== " + mno);
    log.info("team = " + team);
    Iterator<String> itr =  uploadedFile.getFileNames();
    if(itr.hasNext()) {
      MultipartFile mpf = uploadedFile.getFile(itr.next());
      
      String path=uploadedFile.getServletContext().getRealPath("/");
      String sep = System.getProperty("file.separator");
      String fileName = MultipartHelper.generateFilename(mpf.getOriginalFilename());
      String filePath = path+ sep + "team" + sep + "img" + sep;
      
      System.out.println(mpf.getOriginalFilename() +" uploaded!");
      try {
        MultipartHelper.generateFile(mpf, filePath+fileName);
        MultipartHelper.generateThumbnail(filePath, fileName, MultipartHelper.CATE_TEAM); // 리스트 섬네일
        MultipartHelper.generateThumbnail(filePath, fileName, MultipartHelper.CATE_COMPDETAIL_TEAM); // 대회 디테일 섬네일
        team.setEmblem(fileName);
      } catch (IOException e) {
        e.printStackTrace();
        return new AjaxResult("failure", null);
      }
    }
    
    teamService.register(team);
    
    List<Team> teams = teamService.getList();
    Team lastTeam = teams.get(teams.size() - 1);
    
    JoinTeam joinTeam = new JoinTeam();
    joinTeam.setTno(lastTeam.getTno());
    joinTeam.setMno(Integer.parseInt(mno));
    joinTeam.setLevel(1);
    joinTeamService.register(joinTeam);
    return new AjaxResult("success", null);
  }
  
  @RequestMapping("detail")
  public Object detail(int tno) throws Exception {
    Team team = teamService.retrieve(tno);
    return new AjaxResult("success", team);
  }
  
  @RequestMapping(value="update", method=RequestMethod.POST)
  public AjaxResult update(Team team, MultipartHttpServletRequest uploadedFile) throws Exception {
    
    log.info("team = " + team);
    if(team.getEmblem() == null) {
      Iterator<String> itr =  uploadedFile.getFileNames();
      if(itr.hasNext()) {
        MultipartFile mpf = uploadedFile.getFile(itr.next());
        
        String path=uploadedFile.getServletContext().getRealPath("/");
        String sep = System.getProperty("file.separator");
        String fileName = MultipartHelper.generateFilename(mpf.getOriginalFilename());
        String filePath = path+ sep + "team" + sep + "img" + sep;
        
        System.out.println(mpf.getOriginalFilename() +" uploaded!");
        try {
          MultipartHelper.generateFile(mpf, filePath+fileName);
          MultipartHelper.generateThumbnail(filePath, fileName, MultipartHelper.CATE_TEAM); // 리스트 섬네일
          MultipartHelper.generateThumbnail(filePath, fileName, MultipartHelper.CATE_COMPDETAIL_TEAM); // 대회 디테일 섬네일
          team.setEmblem(fileName);
        } catch (IOException e) {
          e.printStackTrace();
          return new AjaxResult("failure", null);
        }
      }
    }
    if (teamService.change(team) <= 0) {
      return new AjaxResult("failure", null);
    }
    
    return new AjaxResult("success", null);
  }
  
  @RequestMapping("delete")
  public AjaxResult delete(int tno) throws Exception {
    if (teamService.remove(tno) <= 0) {
      return new AjaxResult("failure", null);
    }
    
    return new AjaxResult("success", null);
  }
  
  
  @RequestMapping("detailncont")
  public Object getDetailNContent(int tno, int cno) throws Exception {
    HashMap<String, Object> resultMap = new HashMap<>();
    
    resultMap.put("team", teamService.retrieve(tno));
    resultMap.put("content", joinCompService.getContent(tno, cno));
    return new AjaxResult("success", resultMap);
  }
  
  
   
  
}

