package allstar.pms.service.support;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import allstar.pms.dao.TeamDao;
import allstar.pms.domain.Team;
import allstar.pms.service.TeamService;

@Service
public class DefaultTeamService implements TeamService {
  @Autowired TeamDao teamDao;

  public List<Team> getList() {
    return teamDao.selectAll();
  }
  
  public List<Team> getCount(String event, String addr, String possible, String play, String enroll) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("event", event);
    paramMap.put("addr", addr);
    paramMap.put("possible", possible);
    paramMap.put("play", play);
    paramMap.put("enroll", enroll);
    
    return teamDao.selectCount(paramMap);
  }
  
  public List<Team> getTeamList(int pageNo, int pageSize, String event,
      String addr, String possible, String play, String enroll) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("event", event);
    paramMap.put("addr", addr);
    paramMap.put("possible", possible);
    paramMap.put("play", play);
    paramMap.put("enroll", enroll);
    
    return teamDao.selectList(paramMap);
  }
 
  public void register(Team team) {
    teamDao.insert(team);
  }
  
  public Team retrieve(int no) {
    return teamDao.selectOne(no);
  }
  
  public int change(Team team) {
    return teamDao.update(team);
  }
  
  public int remove(int no) {
    return teamDao.delete(no);
  }

  @Override
  public String getEmblemByTno(int tno) {
    return teamDao.selectEmblem(tno);
  }
}
