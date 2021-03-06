package allstar.pms.dao;

import java.util.List;
import java.util.Map;

import allstar.pms.domain.JoinComp;

public interface JoinCompDao {
  List<JoinComp> selectListByCno(int cno);
  
  List<JoinComp> selectListByTno(int tno);
  
  int insert(JoinComp joinComp);
  
  int delete(JoinComp joinComp);
  
  int selectOne(JoinComp joinComp);
  
  List<Integer> selectTnoList(int cno);
  
  String selectContentByCTno(Map<String, Integer> paramMap);
  
  int updateState(Map<String, Integer> paramMap);
  
  List<Integer> selectTnoListState1(int cno);
  
  List<JoinComp> selectJoinedComp(int mno);
  
  int selectState(Map<String, Integer> paramMap);
}
