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
  public int countAllBoard(String event, String date, String search1, String search2) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("event", event);
    paramMap.put("date", date);
    paramMap.put("search1", search1);
    paramMap.put("search2", search2);
    
    return boardDao.selectCountAll(paramMap);
  }
  
  @Override
  public List<Board> getBoardList(String keyword, int pageNo, int pageSize, 
      String event, String date, String reply, String search1, String search2) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("event", event);
    paramMap.put("date", date);
    paramMap.put("reply", reply);
    paramMap.put("search1", search1);
    paramMap.put("search2", search2);
    paramMap.put("keyword", keyword);
    
    return boardDao.selectList(paramMap);
  }
  
  @Override
  public List<Board> getBoardList(String keyword, int pageNo, int pageSize, int eno, 
      String event, String date, String reply, String search1, String search2) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("eno", eno);
    paramMap.put("event", event);
    paramMap.put("date", date);
    paramMap.put("reply", reply);
    paramMap.put("search1", search1);
    paramMap.put("search2", search2);
    paramMap.put("keyword", keyword);
    
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
  public Board retrieve(int no) {
    return boardDao.selectOne(no);
  }
  
  @Override public int upView(int no) {
    boardDao.upView(no);
    return boardDao.selectViews(no);
  }
}