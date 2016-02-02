package allstar.pms.service.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import allstar.pms.dao.JoinTeamDao;
import allstar.pms.domain.JoinTeam;
import allstar.pms.service.JoinTeamService;

@Service
public class DefaultJoinTeamService implements JoinTeamService {
  @Autowired JoinTeamDao joinTeamDao;
  
  
  @Override
  public List<JoinTeam> getJoinTeamByTeam(int tno) {

    return joinTeamDao.selectListByTeam(tno);
  }
  
  @Override
  public List<JoinTeam> getJoinTeamByMember(int mno) {

    
    return joinTeamDao.selectListByMember(mno);
  }

  @Override
  public List<JoinTeam> getCaptainTeamByMember(int mno) {
    return joinTeamDao.selectCaptain(mno);
  }
  
  @Override
  public void register(JoinTeam joinTeam) {
    joinTeamDao.insert(joinTeam);
  }
  
  @Override
  public int remove(int mno) {
    return joinTeamDao.delete(mno);
  }
  
  @Override
  public int changeState(JoinTeam joinTeam) {
    return joinTeamDao.updateState(joinTeam);
  }
 
  @Override
  public int changeLevel(int mno) {
    return joinTeamDao.updateLevel(mno);
  }
  
  @Override
  public JoinTeam retrieve(JoinTeam joinTeam) {
    return joinTeamDao.selectOneByTeamMember(joinTeam);
  }
}