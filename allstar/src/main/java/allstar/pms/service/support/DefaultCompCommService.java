package allstar.pms.service.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import allstar.pms.dao.CompCommDao;
import allstar.pms.domain.CompComm;
import allstar.pms.service.CompCommService;

@Service
public class DefaultCompCommService implements CompCommService {
  @Autowired CompCommDao compCommDao;

  @Override
  public List<CompComm> getCompCommListByComp(int cno) {
    return compCommDao.selectListByComp(cno);
  }

  @Override
  public int register(CompComm compComm) {
    return compCommDao.insert(compComm);
  }

  @Override
  public int remove(int ccno) {
    return compCommDao.delete(ccno);
  }

  @Override
  public int countAllCommFromComp(int cno) {
    return compCommDao.selectCountFromComp(cno);
  }
  
}