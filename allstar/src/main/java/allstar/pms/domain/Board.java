package allstar.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Board  implements Serializable {
  private static final long serialVersionUID = 1L;

  protected int       no;
  protected int       eno;
  protected int       mno;
  protected String    title;
  protected String    content;
  protected Date      createDate;
  protected int       views;
  
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
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
  public Date getCreateDate() {
    return createDate;
  }
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
  public int getViews() {
    return views;
  }
  public void setViews(int views) {
    this.views = views;
  }
  @Override
  public String toString() {
    return "Board [no=" + no + ", eno=" + eno + ", mno=" + mno + ", title=" + title + ", content=" + content
        + ", createDate=" + createDate + ", views=" + views + "]";
  }
    
}
