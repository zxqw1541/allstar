package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.Competition;

public interface CompetitionService {
  
  List<Competition> getCompetitionList(int pageNo, int pageSize, String keyword, String align, String search1, String search2);

  void register(Competition competition);
  
  int remove(int no);
  
  int change(Competition competition);
  
  Competition retrieve(int no);
  
  int countAllCompetition(String search1, String search2);
  
}
