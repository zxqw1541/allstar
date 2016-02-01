package allstar.pms.domain;

import java.io.Serializable;

public class LikeEvent implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected int mno;
  protected int eno;
  protected Event event;
  
  public int getMno() {
    return mno;
  }
  public void setMno(int mno) {
    this.mno = mno;
  }
  public int getEno() {
    return eno;
  }
  public void setEno(int eno) {
    this.eno = eno;
  }
  public Event getEvent() {
    return event;
  }
  public void setEvent(Event event) {
    this.event = event;
  }
  @Override
  public String toString() {
    return "LikeEvent [mno=" + mno + ", eno=" + eno + ", event=" + event + "]";
  }
 
}
