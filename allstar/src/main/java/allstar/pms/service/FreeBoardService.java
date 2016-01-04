package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.FreeBoard;

public interface FreeBoardService {
  List<FreeBoard> getFreeBoardList(int pageNo, int pageSize,
      String keyword, String align);
  void register(FreeBoard freeBoard);
  void remove(int no, String password);
  void change(FreeBoard freeBoard);
  FreeBoard retieve(int no);
}
