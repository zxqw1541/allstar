package allstar.pms.dao;

import java.util.List;
import java.util.Map;

import allstar.pms.domain.Board;

public interface BoardDao {
  List<Board> selectList(Map<String,Object> paramMap);
  
  int insert(Board board);
  
  int delete(Map<String,Object> paramMap);
  
  int update(Board board);

  Board selectOne(int no);
}







