package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.Like_Event;

public interface Like_EventService {
  List<Like_Event> getLike_EventList();
  int register(Like_Event like_event);
  int remove(int mno);
}