package allstar.pms.dao;

import java.util.List;
import java.util.Map;

import allstar.pms.domain.Competition;

public interface CompetitionDao {
  
  List<Competition> selectList(Map<String,Object> paramMap);
  
  int insert(Competition competition);
  
  int delete(int no);
  
  int update(Competition competition);
  
  Competition selectOne(int no);

}
