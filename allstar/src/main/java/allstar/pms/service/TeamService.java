package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.Team;

public interface TeamService {
  List<Team> getList();
  List<Team> getCount(String event, String addr, String possible, String play, String enroll);
  List<Team> getTeamList(int pageNo, int pageSize, String event, 
      String addr, String possible, String play, String enroll);
  void register(Team team);
  Team retrieve(int no);
  int changeJoinCount(int tno);
  int changeTeamScore(int win, int lose, String name);
  int change(Team team);
  int remove(int no);
  Team getEmblemByTno(int tno);
  
  List<Team> getTeamListByTnoEno(int tno, int eno);
  int changeMinusCount(int tno);
}
