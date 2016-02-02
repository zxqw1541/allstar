package allstar.pms.domain;

import java.io.Serializable;

public class Member implements Serializable {
  private static final long serialVersionUID = 1L;
  protected int mno;
protected String id;
protected String email;
protected String tel;
protected String name;
protected String pwd;
protected String photo;
protected String pst_no;
protected String bas_addr;
protected String address;
protected String gender;
protected int age;
protected String introduce;
protected LikeEvent likeEvent;

@Override
public String toString() {
  return "Member [mno=" + mno + ", id=" + id + ", email=" + email + ", tel=" + tel + ", name=" + name + ", pwd=" + pwd
      + ", photo=" + photo + ", pst_no=" + pst_no + ", bas_addr=" + bas_addr + ", address=" + address + ", gender="
      + gender + ", age=" + age + ", introduce=" + introduce + ", likeEvent=" + likeEvent + "]";
}

public int getMno() {
  return mno;
}

public void setMno(int mno) {
  this.mno = mno;
}

public String getId() {
  return id;
}

public void setId(String id) {
  this.id = id;
}

public String getEmail() {
  return email;
}

public void setEmail(String email) {
  this.email = email;
}

public String getTel() {
  return tel;
}

public void setTel(String tel) {
  this.tel = tel;
}

public String getName() {
  return name;
}

public void setName(String name) {
  this.name = name;
}

public String getPwd() {
  return pwd;
}

public void setPwd(String pwd) {
  this.pwd = pwd;
}

public String getPhoto() {
  return photo;
}

public void setPhoto(String photo) {
  this.photo = photo;
}

public String getPst_no() {
  return pst_no;
}

public void setPst_no(String pst_no) {
  this.pst_no = pst_no;
}

public String getBas_addr() {
  return bas_addr;
}

public void setBas_addr(String bas_addr) {
  this.bas_addr = bas_addr;
}

public String getAddress() {
  return address;
}

public void setAddress(String address) {
  this.address = address;
}

public String getGender() {
  return gender;
}

public void setGender(String gender) {
  this.gender = gender;
}

public int getAge() {
  return age;
}

public void setAge(int age) {
  this.age = age;
}

public String getIntroduce() {
  return introduce;
}

public void setIntroduce(String introduce) {
  this.introduce = introduce;
}

public LikeEvent getLikeEvent() {
  return likeEvent;
}

public void setLikeEvent(LikeEvent likeEvent) {
  this.likeEvent = likeEvent;
}
}