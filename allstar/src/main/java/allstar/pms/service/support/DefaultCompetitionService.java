package allstar.pms.service.support;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import allstar.pms.dao.CompetitionDao;
import allstar.pms.domain.Competition;
import allstar.pms.service.CompetitionService;

@Service
public class DefaultCompetitionService implements CompetitionService {
  @Autowired CompetitionDao competitionDao;

  @Override
  public List<Competition> getCompetitionList(int pageNo, int pageSize, String keyword, String align) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);
    
    return competitionDao.selectList(paramMap);
  }

  @Override
  public void register(Competition competition) {
    competitionDao.insert(competition);
  }

  @Override
  public void remove(int id) {
    competitionDao.delete(id);
    
  }

  @Override
  public void change(Competition competition) {
    competitionDao.update(competition);
  }

  @Override
  public Competition retrieve(int no) {
    return competitionDao.selectOne(no);
  }
  

}
