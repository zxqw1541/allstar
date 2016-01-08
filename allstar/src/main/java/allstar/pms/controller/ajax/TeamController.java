package allstar.pms.controller.ajax;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import allstar.pms.domain.AjaxResult;
import allstar.pms.domain.Event;
import allstar.pms.domain.Team;
import allstar.pms.service.EventService;
import allstar.pms.service.TeamService;

@Controller("ajax.TeamController")
@RequestMapping("/team/ajax/*")
public class TeamController {

  @Autowired TeamService teamService;
  @Autowired EventService eventService;

  @RequestMapping("list")
  public Object list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="10") int pageSize,
      @RequestParam(defaultValue="no") String keyword,
      @RequestParam(defaultValue="desc") String align) throws Exception {

    List<Team> teams = teamService.getTeamList(pageNo, pageSize, keyword, align);
    List<Event> events = eventService.getEventList();
    
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("teams", teams);
    resultMap.put("events", events);

    return resultMap;
  }
  
  @RequestMapping(value="add", method=RequestMethod.POST)
  public AjaxResult add(Team team) throws Exception {
    teamService.register(team);
    
    return new AjaxResult("success", null);
  }
  
  @RequestMapping("detail")
  public Object detail(int tno) throws Exception {
    Team team = teamService.retrieve(tno);
    return new AjaxResult("success", team);
  }
  
  @RequestMapping(value="update", method=RequestMethod.POST)
  public AjaxResult update(Team team) throws Exception {
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
  
  
}

