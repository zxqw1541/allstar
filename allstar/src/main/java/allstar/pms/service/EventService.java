package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.Event;

public interface EventService {
  List<Event> getEevnetList();
  void register(Event event);
  void remove(int no);
}
