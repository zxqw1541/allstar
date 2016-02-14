package allstar.pms.dao;

import java.util.List;
import java.util.Map;

import allstar.pms.domain.JoinTeam;

public interface JoinTeamDao {
  
  List<JoinTeam> selectListByTeam(int tno);

  List<JoinTeam> selectListByMember(int mno);

  List<JoinTeam> selectOpenListByMember(int mno);
  
  List<JoinTeam> selectCaptain(int mno);

  String selectAformByTeamMember(Map<String,Object> paramMap);
  
  JoinTeam selectOneByTeamMember(JoinTeam joinTeam);
  
  int updateState(JoinTeam joinTeam);
  
  int updateLevel(int mno);

  int insert(JoinTeam joinTeam);

  int delete(int mno);
  
  int selectStateByTnoMno(JoinTeam joinTeam);
}
