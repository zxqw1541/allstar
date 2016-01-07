package allstar.pms.dao;

import java.util.List;

import allstar.pms.domain.Event;

public interface EventDao {
  List<Event> selectList();
}
