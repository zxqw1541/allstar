package allstar.pms.dao;

import java.util.List;

import allstar.pms.domain.LikeEvent;

public interface LikeEventDao {
  List<LikeEvent> selectList();
  int insert(int mno, int eno);
  int delete(int mno);
}


