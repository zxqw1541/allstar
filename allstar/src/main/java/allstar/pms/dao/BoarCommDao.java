package allstar.pms.dao;

import java.util.List;

import allstar.pms.domain.BoarComm;

public interface BoarCommDao {
  
  List<BoarComm> selectListByBoard(int bno);
  
  int insert(BoarComm boarComm);
  
  int delete(int bcno);
}







