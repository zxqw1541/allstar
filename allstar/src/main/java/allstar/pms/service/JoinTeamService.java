package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.JoinTeam;

public interface JoinTeamService {
  
  List<JoinTeam> getJoinTeamByTeam(int tno);
  
  List<JoinTeam> getJoinTeamByMember(int mno);
  
  int retrieve(JoinTeam joinTeam);
 
  void register(JoinTeam joinTeam);
  
  int remove(int mno);
  
  int changeState(int mno);

}







