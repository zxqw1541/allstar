package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.Board;

public interface BoardService {
  
  List<Board> getBoardList(String keyword, int pageNo, int pageSize, String event, String date, 
      String reply, String search1, String search2);
  
  List<Board> getBoardList(String keyword, int pageNo, int pageSize, int eno, String event, 
      String date, String reply, String search1, String search2);
  
  void register(Board board);
  
  int remove(int bno);
  
  int change(Board board);
  
  Board retrieve(int bno);
  
  int countAllBoard(String event, String date, String search1, String search2);
  
  int  upView(int no);
  
  
}







