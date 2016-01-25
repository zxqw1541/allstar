package allstar.pms.service.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import allstar.pms.dao.LikeEventDao;
import allstar.pms.domain.LikeEvent;
import allstar.pms.service.LikeEventService;

@Service
public class DefaultLikeEventService implements LikeEventService {
  @Autowired LikeEventDao likeEventDao;

  @Override
  public List<LikeEvent> getLikeEventList() {
    return likeEventDao.selectList();
  }

  @Override
  public void register(int mno, int eno) {
    likeEventDao.insert(mno, eno);
  }

  @Override
  public int remove(int mno) {
    return likeEventDao.delete(mno);
  }
  
}