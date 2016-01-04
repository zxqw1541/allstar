package allstar.pms.dao;

import java.util.List;
import java.util.Map;

import allstar.pms.domain.FreeBoard;

public interface FreeBoardDao {
  List<FreeBoard> selectList(Map<String,Object> paramMap);
  
  int insert(FreeBoard freeBoard);
  
  int delete(Map<String,Object> paramMap);
  
  int update(FreeBoard freeBoard);
  
  FreeBoard selectOne(int no);
}
