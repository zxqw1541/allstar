package allstar.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class JoinTeam implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected int     mno;
  protected int     tno;
  protected Date    createdDate;
  protected String  content;
  protected int     state;
  protected int     level;
  protected Team    team;
  protected Member  member;
 
  public Team getTeam() {
    return team;
  }
  public void setTeam(Team team) {
    this.team = team;
  }
  public Member getMember() {
    return member;
  }
  public void setMember(Member member) {
    this.member = member;
  }
  public int getMno() {
    return mno;
  }
  public void setMno(int mno) {
    this.mno = mno;
  }
  public int getTno() {
    return tno;
  }
  public void setTno(int tno) {
    this.tno = tno;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
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
  public int getLevel() {
    return level;
  }
  public void setLevel(int level) {
    this.level = level;
  }
  @Override
  public String toString() {
    return "JoinTeam [mno=" + mno + ", tno=" + tno + ", createdDate=" + createdDate + ", content=" + content
        + ", state=" + state + ", level=" + level + ", team=" + team + ", member=" + member + "]";
  }

}
