package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.LikeEvent;

public interface LikeEventService {
  List<LikeEvent> getLikeEventList();
  
  List<LikeEvent> getLikeEventListByMno(int mno);
  
  void register(LikeEvent likeEvent);
  
  int remove(int mno);
}
