package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.Event;

public interface EventService {
  List<Event> getEevnetList();
  int register(Event event);
  int remove(int no);
}
