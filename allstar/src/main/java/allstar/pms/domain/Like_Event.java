package allstar.pms.domain;

import java.io.Serializable;

public class Like_Event implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected int mno;
  protected int eno;
  
  
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


}
