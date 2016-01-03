package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.Team;

public interface TeamService {
  List<Team> getTeamList(int pageNo, int pageSize,
      String keyword, String align);
  void register(Team team);
  Team retrieve(int no);
  int change(Team team);
  int remove(int no);

}
