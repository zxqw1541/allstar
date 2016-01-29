package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.BoarComm;

public interface BoarCommService {
  
  List<BoarComm> getBoarCommListByBoard(int bno);
  
  void register(BoarComm boarComm);
  
  int remove(int bcno);

  int countAllCommFromBoard(int bno);
}







