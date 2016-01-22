package allstar.pms.dao;

import java.util.List;

import allstar.pms.domain.Like_Event;

public interface Like_EventDao {
  List<Like_Event> selectList();
  int insert(int mno, int eno);
  int delete(int mno);
}


