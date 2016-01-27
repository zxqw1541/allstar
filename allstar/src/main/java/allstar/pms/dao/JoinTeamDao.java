package allstar.pms.dao;

import java.util.List;

import allstar.pms.domain.JoinTeam;

public interface JoinTeamDao {
  
  List<JoinTeam> selectListByTeam(int tno);

  List<JoinTeam> selectListByMember(int mno);
  
  int selectOneByTeamMember(JoinTeam joinTeam);

  int updateState(int mno);

  int insert(JoinTeam joinTeam);

  int delete(int mno);
}
