package allstar.pms.dao;

import java.util.List;

import allstar.pms.domain.JoinComp;

public interface JoinCompDao {
  List<JoinComp> selectListByCno(int cno);
  
  List<JoinComp> selectListByTno(int tno);
  
  int insert(JoinComp joinComp);
  
  int delete(JoinComp joinComp);
  
  int selectOne(JoinComp joinComp);
  
  List<Integer> selectTnoList(int cno);
}
