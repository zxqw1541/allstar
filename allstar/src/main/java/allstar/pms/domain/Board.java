package allstar.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Board  implements Serializable {
  private static final long serialVersionUID = 1L;

  protected int       bno;
  protected String    bevent;
  protected String    title;
  protected String    content;
  protected Date      cre_dt;
  protected String    bphoto; // 컬럼명 = afile
  
  @Override
  public String toString() {
    return "Board [bno=" + bno + ", bevent=" + bevent + ", title=" + title + ", content=" + content + ", cre_dt="
        + cre_dt + ", bphoto=" + bphoto + "]";
  }

  public int getBno() {
    return bno;
  }

  public void setBno(int bno) {
    this.bno = bno;
  }

  public String getBevent() {
    return bevent;
  }

  public void setBevent(String bevent) {
    this.bevent = bevent;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCre_dt() {
    return cre_dt;
  }

  public void setCre_dt(Date cre_dt) {
    this.cre_dt = cre_dt;
  }

  public String getBphoto() {
    return bphoto;
  }

  public void setBphoto(String bphoto) {
    this.bphoto = bphoto;
  }
}
