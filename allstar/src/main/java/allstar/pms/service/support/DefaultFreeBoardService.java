package allstar.pms.service.support;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import allstar.pms.dao.FreeBoardDao;
import allstar.pms.domain.FreeBoard;
import allstar.pms.service.FreeBoardService;

@Service
public class DefaultFreeBoardService implements FreeBoardService {
  @Autowired FreeBoardDao freeBoardDao;
  
  public List<FreeBoard> getFreeBoardList(int pageNo, int pageSize,
      String keyword, String align) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);
    
    return freeBoardDao.selectList(paramMap); 
  }
  
  public void register(FreeBoard freeBoard) {
    freeBoardDao.insert(freeBoard);
  }
  
  public void remove(int no, String password) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", no);
    paramMap.put("password", password);
    
    freeBoardDao.delete(paramMap);
  }
  
  public void change(FreeBoard freeBoard) {
    freeBoardDao.update(freeBoard);
  }
  
  public FreeBoard retieve(int no) {
    return freeBoardDao.selectOne(no);
  }
  
}
