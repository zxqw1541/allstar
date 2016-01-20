package allstar.pms.dao;

import java.util.List;

import allstar.pms.domain.Like_Event;

public interface Like_EventDao {
  List<Like_Event> selectList();
  int insert(Like_Event like_event);
  int delete(int mno);
}


