package allstar.pms.util;

import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import allstar.pms.domain.Team;

public class TournamentHelper {
  
  /*
   * 랜덤으로 숫자만큼 풀세팅 해줌
   */
  @SuppressWarnings("unchecked")
  public static String makeTournament(List<Team> joinTeamList) {
    System.out.println("=============================================");
    System.out.println("TeamList => " + joinTeamList);
    System.out.println("=============================================");
    int realCount = joinTeamList.size();
    int teamCount = makePowerOfTwo(realCount);
    int undefinedCount = 0;

    JSONObject jsonroot = new JSONObject();
    
    JSONArray teamList = new JSONArray();
    for (int i = 0; i < teamCount; i+=2) {
      JSONArray vsTeam = new JSONArray();
      
      if (realCount % 2 == 0) {
        if (i >= realCount) {
          vsTeam.add("undefined" + ++undefinedCount);
          vsTeam.add("undefined" + ++undefinedCount);
        } else {
          vsTeam.add(joinTeamList.get(i).getName());
          vsTeam.add(joinTeamList.get(i + 1).getName());
        }
      } else {
        if (i == (realCount-1)) {
          vsTeam.add(joinTeamList.get(i).getName());
          vsTeam.add("undefined" + ++undefinedCount);
        } else if (i > (realCount-1)) {
          vsTeam.add("undefined" + ++undefinedCount);
          vsTeam.add("undefined" + ++undefinedCount);
        } else {
          vsTeam.add(joinTeamList.get(i).getName());
          vsTeam.add(joinTeamList.get(i + 1).getName());
        }
      }
      teamList.add(vsTeam);
    }    
    jsonroot.put("teams", teamList);
    
    Random r = new Random();
    JSONArray results = new JSONArray();
    
    for (int i = teamList.size(); i > 0 ; i/=2) {
      JSONArray rounds = new JSONArray();    
      int k = i;
      if (i == 1) {
        k = 2;
      }
      for (int j = 0; j < k; j++) {
        int a = r.nextInt(15);
        int b = 0;
        do {
          b = r.nextInt(15);
        } while(a == b);

        JSONArray score = new JSONArray();
        score.add(0);
        score.add(0);
        rounds.add(score);
      }
      results.add(rounds);
    }
    jsonroot.put("results", results);
    
    System.out.println("=============================================");
    System.out.println("JSONObject => " + jsonroot);
    System.out.println("=============================================");
    
    return jsonroot.toJSONString();
  }
  
  public static int makePowerOfTwo(int num) {
    if ( num == 1 || (num & (num - 1)) != 0) {
      System.out.println(num + "는 제곱수 아님 or 1");
      int count = 0 ;
      while (num != 1) {
        num = num >> 1;
        count++;
      }
      for (int i = 0; i <= count; i++) {
        num = num << 1;
      }
    }
    return num;
  }
  

}
