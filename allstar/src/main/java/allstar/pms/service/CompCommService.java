package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.CompComm;

public interface CompCommService {
  
  List<CompComm> getCompCommListByComp(int cno);
  
  int register(CompComm compComm);
  
  int remove(int ccno);

  int countAllCommFromComp(int cno);
}







