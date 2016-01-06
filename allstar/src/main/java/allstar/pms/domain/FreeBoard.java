package allstar.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class FreeBoard implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected int fno;
  protected String title;
  protected String content;
  protected Date createdDate;
  protected int views;
  
  @Override
  public String toString() {
    return "FreeBoard [fno=" + fno + ", title=" + title + ", content=" + content + ", createDate=" + createdDate
        + ", views=" + views + "]";
  }

  public int getFno() {
    return fno;
  }
  public void setFno(int fno) {
    this.fno = fno;
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
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createDate) {
    this.createdDate = createDate;
  }
  public int getViews() {
    return views;
  }
  public void setViews(int views) {
    this.views = views;
  }
  
  
}
