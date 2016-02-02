package allstar.pms.service;

import java.util.List;
import java.util.Map;

import allstar.pms.domain.JoinComp;

public interface JoinCompService {
  
  List<JoinComp> getJoinListByCno(int cno);
  
  List<JoinComp> getJoinListByTno(int tno);

  int register(JoinComp joinComp);
  
  int remove(JoinComp joinComp);
  
  int retrive(JoinComp joinComp);
  
  List<Integer> getTnoList(int cno);
  
  String getContent(int tno, int cno);
  
  int changeState(int tno, int cno, int state);
}
