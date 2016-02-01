package allstar.pms.dao;

import java.util.List;
import java.util.Map;

import allstar.pms.domain.Team;

public interface TeamDao {
  List<Team> selectAll();
  List<Team> selectCount(Map<String,Object> paramMap);
  List<Team> selectList(Map<String,Object> paramMap);
  Team selectOne(int no);
  int insert(Team team);
  int delete(int no);
  int update(Team team);
  int joinCount(int tno);
  Team selectEmblem(int tno);
  List<Team> selectTeamByMnoEno(Map<String,Integer> paramMap);
}
