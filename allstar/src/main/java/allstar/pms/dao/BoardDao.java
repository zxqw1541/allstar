package allstar.pms.dao;

import java.util.List;
import java.util.Map;

import allstar.pms.domain.Board;

public interface BoardDao {
  int selectCountAll(Map<String,Object> paramMap);
  
  List<Board> selectList(Map<String,Object> paramMap);
  
  int insert(Board board);
  
  int delete(int bno);
  
  int update(Board board);

  Board selectOne(int bno);
  
  int upView(int no);
  
  List<Board> selectBpn();
}







