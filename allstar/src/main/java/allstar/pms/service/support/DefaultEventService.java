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
  public List<Event> getEevnetList() {
    return eventDao.selectList();
  }

  @Override
  public void register(Event event) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void remove(int no) {
    // TODO Auto-generated method stub
    
  }
  
}