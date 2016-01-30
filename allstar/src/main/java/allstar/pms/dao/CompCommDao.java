package allstar.pms.dao;

import java.util.List;

import allstar.pms.domain.CompComm;

public interface CompCommDao {
  
  List<CompComm> selectListByComp(int cno);

  int insert(CompComm compComm);
  
  int delete(int ccno);
  
  int selectCountFromComp(int cno);
}







