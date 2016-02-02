package allstar.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class JoinComp implements Serializable {

  private static final long serialVersionUID = 1L;
  
  protected int cno;
  protected int tno;
  protected Date createDate;
  protected String content;
  protected int state;
  protected String rank;
  protected Team team;
  public int getCno() {
    return cno;
  }
  public void setCno(int cno) {
    this.cno = cno;
  }
  public int getTno() {
    return tno;
  }
  public void setTno(int tno) {
    this.tno = tno;
  }
  public Date getCreateDate() {
    return createDate;
  }
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public int getState() {
    return state;
  }
  public void setState(int state) {
    this.state = state;
  }
  public String getRank() {
    return rank;
  }
  public void setRank(String rank) {
    this.rank = rank;
  }
  public Team getTeam() {
    return team;
  }
  public void setTeam(Team team) {
    this.team = team;
  }
  @Override
  public String toString() {
    return "JoinComp [cno=" + cno + ", tno=" + tno + ", createDate=" + createDate + ", content=" + content + ", state="
        + state + ", rank=" + rank + ", team=" + team + "]";
  }
  
}
