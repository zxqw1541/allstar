package allstar.pms.service.support;

import java.util.List;

import org.springframework.stereotype.Service;

import allstar.pms.domain.Event;
import allstar.pms.domain.FreeBoard;
import allstar.pms.service.EventService;

@Service
public class DefaultEventService implements EventService {
  

  @Override
  public List<FreeBoard> getEevnetList() {
    // TODO Auto-generated method stub
    return null;
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