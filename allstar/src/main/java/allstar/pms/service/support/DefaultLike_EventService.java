package allstar.pms.service.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import allstar.pms.dao.Like_EventDao;
import allstar.pms.domain.Like_Event;
import allstar.pms.service.Like_EventService;

@Service
public class DefaultLike_EventService implements Like_EventService {
  @Autowired Like_EventDao eventDao;

  @Override
  public List<Like_Event> getLike_EventList() {
    return eventDao.selectList();
  }

  @Override
  public void register(int mno, int eno) {
    eventDao.insert(mno, eno);
  }

  @Override
  public int remove(int mno) {
    return eventDao.delete(mno);
  }
  
}