package allstar.pms.service;

import java.util.List;

import allstar.pms.domain.Competition;

public interface CompetitionService {
  
  List<Competition> getCompetitionList(int pageNo, int pageSize, 
      String event, String addr, String recruit, String start, String reply, String search1, String search2);

  void register(Competition competition);
  
  int remove(int no);
  
  int change(Competition competition);
  
  int changeTournament(int no, String operation);
  
  Competition retrieve(int no);
  
  int countAllCompetition(String event, String addr, String search1, String search2);
  
  int getMnoByCno(int cno);
  
  int plus1JoinNum(int cno);
  
  int minus1JoinNum(int cno);
  
  Competition getJoinNTeamNum(int cno);
 
  int getLastAddCno();
  
  List<Competition> getCompListByMno(int mno);
}
