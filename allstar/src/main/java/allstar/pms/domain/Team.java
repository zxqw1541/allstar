package allstar.pms.domain;

import java.io.Serializable;

public class Team implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected int     no;
  protected String  name;
  protected String  captain;
  protected String  event;
  protected String  hometown;
  protected int     totalNum;
  protected int     joinNum;
  protected int     win;
  protected int     draw;
  protected int     lose;
  protected int     fee;
  protected String  meetDay;
  protected String  introduce;
  protected String  photo;
  
  @Override
  public String toString() {
    return "Team [no=" + no + ", name=" + name + ", captain=" + captain + ", event=" + event + ", hometown=" + hometown
        + ", totalNum=" + totalNum + ", joinNum=" + joinNum + ", win=" + win + ", draw=" + draw + ", lose=" + lose
        + ", fee=" + fee + ", meetDay=" + meetDay + ", introduce=" + introduce + ", photo=" + photo + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCaptain() {
    return captain;
  }

  public void setCaptain(String captain) {
    this.captain = captain;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public String getHometown() {
    return hometown;
  }

  public void setHometown(String hometown) {
    this.hometown = hometown;
  }

  public int getTotalNum() {
    return totalNum;
  }

  public void setTotalNum(int totalNum) {
    this.totalNum = totalNum;
  }

  public int getJoinNum() {
    return joinNum;
  }

  public void setJoinNum(int joinNum) {
    this.joinNum = joinNum;
  }

  public int getWin() {
    return win;
  }

  public void setWin(int win) {
    this.win = win;
  }

  public int getDraw() {
    return draw;
  }

  public void setDraw(int draw) {
    this.draw = draw;
  }

  public int getLose() {
    return lose;
  }

  public void setLose(int lose) {
    this.lose = lose;
  }

  public int getFee() {
    return fee;
  }

  public void setFee(int fee) {
    this.fee = fee;
  }

  public String getMeetDay() {
    return meetDay;
  }

  public void setMeetDay(String meetDay) {
    this.meetDay = meetDay;
  }

  public String getIntroduce() {
    return introduce;
  }

  public void setIntroduce(String introduce) {
    this.introduce = introduce;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }
  
}
