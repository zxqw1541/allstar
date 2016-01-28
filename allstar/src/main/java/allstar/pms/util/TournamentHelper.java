package allstar.pms.util;

import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TournamentHelper {
  
  /*
   * 랜덤으로 숫자만큼 풀세팅 해줌
   */
  @SuppressWarnings("unchecked")
  public static String makeTournament(int teamCount) {
    int realCount = teamCount;
    teamCount = makePowerOfTwo(teamCount);
    System.out.println(teamCount);
    
    JSONObject jsonroot = new JSONObject();
    
    JSONArray teamList = new JSONArray();
    for (int i = 0; i < teamCount; i+=2) {
      JSONArray vsTeam = new JSONArray();
      
      if (realCount % 2 == 0) {
        if (i >= realCount) {
          vsTeam.add("undefined");
          vsTeam.add("undefined");
        } else {
          vsTeam.add("Team" + i);
          vsTeam.add("Team" + (i+1));
        }
      } else {
        if (i == (realCount-1)) {
          vsTeam.add("Team" + i);
          vsTeam.add("undefined");
        } else if (i > (realCount-1)) {
          vsTeam.add("undefined");
          vsTeam.add("undefined");
        } else {
          vsTeam.add("Team" + i);
          vsTeam.add("Team" + (i+1));
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
