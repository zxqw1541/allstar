package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.Board;

public interface BoardService {
  List<Board> getBoardList(
      int pageNo, int pageSize, 
      String keyword, String align);
  void register(Board board);
  void remove(int bno);
  void change(Board board);
  Board retieve(int bno);
}







