package allstar.pms.dao;

import java.util.List;

import allstar.pms.domain.Event;

public interface EventDao {
  List<Event> selectList();
  int insert(Event event);
  int delete(int no);
}
