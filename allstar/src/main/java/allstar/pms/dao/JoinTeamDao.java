package allstar.pms.dao;

import java.util.List;

import allstar.pms.domain.JoinTeam;

public interface JoinTeamDao {
  
  List<JoinTeam> selectListByTeam(int tno);

  List<JoinTeam> selectListByMember(int mno);
  
  List<JoinTeam> selectCaptain(int mno);
  
  JoinTeam selectOneByTeamMember(JoinTeam joinTeam);
  
  int updateState(JoinTeam joinTeam);
  
  int updateLevel(int mno);

  int insert(JoinTeam joinTeam);

  int delete(int mno);
}
