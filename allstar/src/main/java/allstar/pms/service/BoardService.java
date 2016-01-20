package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.Board;

public interface BoardService {
  List<Board> getBoardList(int pageNo, int pageSize, String keyword, String align);
  List<Board> getBoardList(int pageNo, int pageSize, String keyword, String align, int eno);
  void register(Board board);
  int remove(int bno);
  int change(Board board);
  Board retrieve(int bno);
  List<Board> getList();
}







