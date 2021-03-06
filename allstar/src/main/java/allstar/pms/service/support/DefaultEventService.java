package allstar.pms.service.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import allstar.pms.dao.EventDao;
import allstar.pms.domain.Event;
import allstar.pms.service.EventService;

@Service
public class DefaultEventService implements EventService {
  @Autowired EventDao eventDao;

  @Override
  public List<Event> getEventList() {
    return eventDao.selectList();
  }

  @Override
  public int register(Event event) {
    return eventDao.insert(event);
  }

  @Override
  public int remove(int no) {
    return eventDao.delete(no);
  }
  
}