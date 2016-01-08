package allstar.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class FreeBoard implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected int no;
  protected int eno=1;
  protected int mno=1;
  protected String title;
  protected String content;
  protected Date createdDate;
  protected int views;

  @Override
  public String toString() {
    return "FreeBoard [fno=" + no + ", eno=" + eno + ", mno=" + mno + ", title=" + title + ", content=" + content
        + ", createdDate=" + createdDate + ", views=" + views + "]";
  }
  
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
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

  public int getEno() {
    return eno;
  }

  public void setEno(int eno) {
    this.eno = eno;
  }

  public int getMno() {
    return mno;
  }

  public void setMno(int mno) {
    this.mno = mno;
  }
  
  
}
