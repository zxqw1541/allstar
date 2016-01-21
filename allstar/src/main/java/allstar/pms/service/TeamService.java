package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.Team;

public interface TeamService {
  List<Team> getList();
  List<Team> getTeamList(int pageNo, int pageSize, String event, 
      String addr, String possible, String play, String enroll);
  void register(Team team);
  Team retrieve(int no);
  int change(Team team);
  int remove(int no);
}
