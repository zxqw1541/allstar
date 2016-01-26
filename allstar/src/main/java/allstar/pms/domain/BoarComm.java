package allstar.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class BoarComm  implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected int bcno;
  protected int mno;
  protected int bno;
  protected String comm;
  protected Date createdDate;
  protected Board board;
  protected Member member;
 
  
  
  
  public Board getBoard() {
    return board;
  }
  public void setBoard(Board board) {
    this.board = board;
  }
  public Member getMember() {
    return member;
  }
  public void setMember(Member member) {
    this.member = member;
  }
  public int getBcno() {
    return bcno;
  }
  public void setBcno(int bcno) {
    this.bcno = bcno;
  }
  public int getMno() {
    return mno;
  }
  public void setMno(int mno) {
    this.mno = mno;
  }
  public int getBno() {
    return bno;
  }
  public void setBno(int bno) {
    this.bno = bno;
  }
  public String getComm() {
    return comm;
  }
  public void setComm(String comm) {
    this.comm = comm;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  
  
  @Override
  public String toString() {
    return "BoarComm [bcno=" + bcno + ", mno=" + mno + ", bno=" + bno + ", comm=" + comm + ", createdDate="
        + createdDate + "]";
  }
}