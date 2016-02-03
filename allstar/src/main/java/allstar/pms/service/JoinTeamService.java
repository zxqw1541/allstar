package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.JoinTeam;

public interface JoinTeamService {
  
  List<JoinTeam> getJoinTeamByTeam(int tno);
  
  List<JoinTeam> getJoinTeamByMember(int mno);

  List<JoinTeam> getOpenTeamByMember(int mno);
  
  List<JoinTeam> getCaptainTeamByMember(int mno);
  
  JoinTeam retrieve(JoinTeam joinTeam);
 
  void register(JoinTeam joinTeam);
  
  int remove(int mno);
  
  int changeState(JoinTeam joinTeam);

  int changeLevel(int mno);

}







