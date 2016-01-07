package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.Event;

public interface EventService {
  List<Event> getEventList();
  int register(Event event);
  int remove(int no);
}
