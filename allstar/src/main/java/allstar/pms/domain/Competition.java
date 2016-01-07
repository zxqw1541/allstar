package allstar.pms.domain;

import java.sql.Date;

public class Competition {
  protected int no;
  protected int eno;
  protected int tno; 
  protected String name;
  protected int teamNum;
  protected int joinNum;
  protected int cost;
  protected Date startDate;
  protected Date endDate;
  protected Date recruitStartDate;
  protected Date recruitEndDate;
  protected String content;
  protected String poster;
  protected String postNo;
  protected String baseAddr;
  protected String operation;
  protected Event event;
  protected Team team;
  
  public Event getEvent() {
    return event;
  }
  public void setEvent(Event event) {
    this.event = event;
  }
  public Team getTeam() {
    return team;
  }
  public void setTeam(Team team) {
    this.team = team;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public int getEno() {
    return eno;
  }
  public void setEno(int eno) {
    this.eno = eno;
  }
  public int getTno() {
    return tno;
  }
  public void setTno(int tno) {
    this.tno = tno;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getTeamNum() {
    return teamNum;
  }
  public void setTeamNum(int teamNum) {
    this.teamNum = teamNum;
  }
  public int getJoinNum() {
    return joinNum;
  }
  public void setJoinNum(int joinNum) {
    this.joinNum = joinNum;
  }
  public int getCost() {
    return cost;
  }
  public void setCost(int cost) {
    this.cost = cost;
  }
  public Date getStartDate() {
    return startDate;
  }
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
  public Date getEndDate() {
    return endDate;
  }
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  public Date getRecruitStartDate() {
    return recruitStartDate;
  }
  public void setRecruitStartDate(Date recruitStartDate) {
    this.recruitStartDate = recruitStartDate;
  }
  public Date getRecruitEndDate() {
    return recruitEndDate;
  }
  public void setRecruitEndDate(Date recruitEndDate) {
    this.recruitEndDate = recruitEndDate;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getPoster() {
    return poster;
  }
  public void setPoster(String poster) {
    this.poster = poster;
  }
  public String getPostNo() {
    return postNo;
  }
  public void setPostNo(String postNo) {
    this.postNo = postNo;
  }
  public String getBaseAddr() {
    return baseAddr;
  }
  public void setBaseAddr(String baseAddr) {
    this.baseAddr = baseAddr;
  }
  public String getOperation() {
    return operation;
  }
  public void setOperation(String operation) {
    this.operation = operation;
  }
  @Override
  public String toString() {
    return "Competition [no=" + no + ", eno=" + eno + ", tno=" + tno + ", name=" + name + ", teamNum=" + teamNum
        + ", joinNum=" + joinNum + ", cost=" + cost + ", startDate=" + startDate + ", endDate=" + endDate
        + ", recruitStartDate=" + recruitStartDate + ", recruitEndDate=" + recruitEndDate + ", content=" + content
        + ", poster=" + poster + ", postNo=" + postNo + ", baseAddr=" + baseAddr + ", operation=" + operation
        + ", event=" + event + ", team=" + team + "]";
  }
}


