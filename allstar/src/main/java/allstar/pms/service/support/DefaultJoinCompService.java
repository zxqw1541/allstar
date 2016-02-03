package allstar.pms.service.support;

import java.util.HashMap;
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

  @Override
  public List<Integer> getTnoList(int cno) {
    return joinCompDao.selectTnoList(cno);
  }
  
  @Override
  public List<Integer> getApprovedTnoList(int cno) {
    return joinCompDao.selectTnoListState1(cno);
  }

  @Override
  public String getContent(int tno, int cno) {
    HashMap<String, Integer> paramMap = new HashMap<>();
    paramMap.put("tno", tno);
    paramMap.put("cno", cno);
    return joinCompDao.selectContentByCTno(paramMap);
  }

  @Override
  public int changeState(int tno, int cno, int state) {
    HashMap<String, Integer> paramMap = new HashMap<>();
    paramMap.put("tno", tno);
    paramMap.put("cno", cno);
    paramMap.put("state", state);
    return joinCompDao.updateState(paramMap);
  }

}
