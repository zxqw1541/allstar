package allstar.pms.domain;

import java.sql.Date;

public class Competition {
  protected int no;
  protected String type;
  protected String event;
  protected String venue;
  protected String name;
  protected int teamNum;
  protected int joinCost;
  protected Date startDate;
  protected Date endDate;
  protected Date recruitStartDate;
  protected Date recruitEndDate;
  protected String content;
  protected String competitionPhoto;
  protected String hostId;
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getEvent() {
    return event;
  }
  public void setEvent(String event) {
    this.event = event;
  }
  public String getVenue() {
    return venue;
  }
  public void setVenue(String venue) {
    this.venue = venue;
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
  public int getJoinCost() {
    return joinCost;
  }
  public void setJoinCost(int joinCost) {
    this.joinCost = joinCost;
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
  public String getCompetitionPhoto() {
    return competitionPhoto;
  }
  public void setCompetitionPhoto(String competitionPhoto) {
    this.competitionPhoto = competitionPhoto;
  }
  public String getHostId() {
    return hostId;
  }
  public void setHostId(String hostId) {
    this.hostId = hostId;
  }
  @Override
  public String toString() {
    return "Competition [no=" + no + ", type=" + type + ", event=" + event + ", venue=" + venue + ", name=" + name
        + ", teamNum=" + teamNum + ", joinCost=" + joinCost + ", startDate=" + startDate + ", endDate=" + endDate
        + ", recruitStartDate=" + recruitStartDate + ", recruitEndDate=" + recruitEndDate + ", content=" + content
        + ", competitionPhoto=" + competitionPhoto + ", hostId=" + hostId + "]";
  }
  
 
}
