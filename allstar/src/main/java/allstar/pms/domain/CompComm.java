package allstar.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class CompComm  implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected int ccno;
  protected int cno;
  protected int mno;
  protected String comm;
  protected Date createdDate;
  protected Member member;
  public int getCcno() {
    return ccno;
  }
  public void setCcno(int ccno) {
    this.ccno = ccno;
  }
  public int getCno() {
    return cno;
  }
  public void setCno(int cno) {
    this.cno = cno;
  }
  public int getMno() {
    return mno;
  }
  public void setMno(int mno) {
    this.mno = mno;
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
  public Member getMember() {
    return member;
  }
  public void setMember(Member member) {
    this.member = member;
  }
  @Override
  public String toString() {
    return "CompComm [ccno=" + ccno + ", cno=" + cno + ", mno=" + mno + ", comm=" + comm + ", createdDate="
        + createdDate + ", member=" + member + "]";
  }
  
  
}