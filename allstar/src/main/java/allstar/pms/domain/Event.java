package allstar.pms.domain;

import java.io.Serializable;

public class Event implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected int     eno;
  protected String  name;
  
  @Override
  public String toString() {
    return "Event [eno=" + eno + ", name=" + name + "]";
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
}
