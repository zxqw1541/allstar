package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.LikeEvent;

public interface LikeEventService {
  List<LikeEvent> getLikeEventList();
  
  void register(int mno, int eno);
  
  int remove(int mno);
}
