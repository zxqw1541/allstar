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
  
  public List<Team> getTeamList(int pageNo, int pageSize,
      String keyword, String align) {

    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);

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
}
