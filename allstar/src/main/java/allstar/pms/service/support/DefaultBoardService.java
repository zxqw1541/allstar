package allstar.pms.service.support;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import allstar.pms.dao.BoardDao;
import allstar.pms.domain.Board;
import allstar.pms.service.BoardService;

@Service
public class DefaultBoardService implements BoardService {
  @Autowired BoardDao boardDao;
  
  @Override
  public List<Board> getBoardList(int pageNo, int pageSize, 
      String keyword, String align) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);
    
    return boardDao.selectList(paramMap);
  }
  
  @Override
  public void register(Board board) {
    boardDao.insert(board);
  }
  
  @Override
  public int remove(int bno) {
    return boardDao.delete(bno);
  }
  
  @Override
  public int change(Board board) {
    return boardDao.update(board);
  }
  
  @Override
  public Board retrieve(int bno) {
    return boardDao.selectOne(bno);
  }
}