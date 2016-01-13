package allstar.pms.dao;

import java.util.List;
import java.util.Map;

import allstar.pms.domain.Team;

public interface TeamDao {
  List<Team> selectAll();
  List<Team> selectList(Map<String,Object> paramMap);
  Team selectOne(int no);
  int insert(Team team);
  int delete(int no);
  int update(Team team);
}
