package allstar.pms.domain;

import java.io.Serializable;

public class Team implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected int     tno;
  protected int     eno;
  protected String  name;
  protected int     totalNum;
  protected int     joinNum;
  protected int     win;
  protected int     draw;
  protected int     lose;
  protected int     fee;
  protected String  meetDay;
  protected String  introduce;
  protected String  emblem;
  protected String  aForm;
  protected String  postNo;
  protected String  baseAddr;
  
  @Override
  public String toString() {
    return "Team [tno=" + tno + ", eno=" + eno + ", name=" + name + ", totalNum=" + totalNum + ", joinNum=" + joinNum
        + ", win=" + win + ", draw=" + draw + ", lose=" + lose + ", fee=" + fee + ", meetDay=" + meetDay
        + ", introduce=" + introduce + ", emblem=" + emblem + ", aForm=" + aForm + ", postNo=" + postNo + ", baseAddr="
        + baseAddr + "]";
  }

  public int getTno() {
    return tno;
  }

  public void setTno(int tno) {
    this.tno = tno;
  }

  public int getEno() {
    return eno;
  }

  public void setEno(int eno) {
    this.eno = eno;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getEmblem() {
    return emblem;
  }

  public void setEmblem(String emblem) {
    this.emblem = emblem;
  }

  public String getaForm() {
    return aForm;
  }

  public void setaForm(String aForm) {
    this.aForm = aForm;
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
  
}
