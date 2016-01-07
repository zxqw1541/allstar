package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.Event;
import allstar.pms.domain.FreeBoard;

public interface EventService {
  List<FreeBoard> getEevnetList();
  void register(Event event);
  void remove(int no);
}
