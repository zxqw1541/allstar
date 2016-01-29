package allstar.pms.util;

import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonTest {
  
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    int teamCount = 64;
    
    if ( teamCount == 1 || (teamCount & (teamCount - 1)) != 0) {
      System.out.println(teamCount + "는 제곱수 아님 or 1");
      int count = 0 ;
      while (teamCount != 1) {
        teamCount = teamCount >> 1;
        count++;
      }
      for (int i = 0; i <= count; i++) {
        teamCount = teamCount << 1;
      }
      System.out.println(teamCount);
    }
    JSONObject jsonroot = new JSONObject();
    
    JSONArray teamList = new JSONArray();
    for (int i = 0; i < teamCount; i+=2) {
      JSONArray vsTeam = new JSONArray();
      vsTeam.add("Team" + i);
      vsTeam.add("Team" + (i+1));
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
        score.add(a);
        score.add(b);
        rounds.add(score);
      }
      results.add(rounds);
    }
    jsonroot.put("results", results);
    
    System.out.println(jsonroot.toJSONString());
  }

}
