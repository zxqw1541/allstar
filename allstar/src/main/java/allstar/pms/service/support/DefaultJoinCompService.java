package allstar.pms.service.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import allstar.pms.dao.JoinCompDao;
import allstar.pms.domain.JoinComp;
import allstar.pms.service.JoinCompService;

@Service
public class DefaultJoinCompService implements JoinCompService {
  @Autowired JoinCompDao joinCompDao;

  @Override
  public List<JoinComp> getJoinListByCno(int cno) {
    return joinCompDao.selectListByCno(cno);
  }

  @Override
  public List<JoinComp> getJoinListByTno(int tno) {
    return joinCompDao.selectListByTno(tno);
  }

  @Override
  public int register(JoinComp joinComp) {
    return joinCompDao.insert(joinComp);
  }

  @Override
  public int remove(JoinComp joinComp) {
    return joinCompDao.delete(joinComp);
  }

  @Override
  public int retrive(JoinComp joinComp) {
    return joinCompDao.selectOne(joinComp);
  }
}