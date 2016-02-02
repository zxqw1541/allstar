package allstar.pms.dao;

import java.util.List;

import allstar.pms.domain.JoinTeam;

public interface JoinTeamDao {
  
  List<JoinTeam> selectListByTeam(int tno);

  List<JoinTeam> selectListByMember(int mno);

  List<JoinTeam> selectOpenListByMember(int mno);
  
  List<JoinTeam> selectCaptain(int mno);

  JoinTeam selectOneByTeamMember(JoinTeam joinTeam);
  
  int updateState(int mno);
  
  int updateLevel(int mno);

  int insert(JoinTeam joinTeam);

  int delete(int mno);
}
