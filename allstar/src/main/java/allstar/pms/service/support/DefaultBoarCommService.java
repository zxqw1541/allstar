package allstar.pms.service.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import allstar.pms.dao.BoarCommDao;
import allstar.pms.domain.BoarComm;
import allstar.pms.service.BoarCommService;

@Service
public class DefaultBoarCommService implements BoarCommService {
  @Autowired BoarCommDao boarCommDao;

  @Override
  public List<BoarComm> getBoarCommListByBoard(int bno) {
   
    return boarCommDao.selectListByBoard(bno);
  }
  
  
  @Override
  public void register(BoarComm boarComm) {
    boarCommDao.insert(boarComm);
  }
  
  @Override
  public int remove(int bcno) {
    return boarCommDao.delete(bcno);
  }
 
}