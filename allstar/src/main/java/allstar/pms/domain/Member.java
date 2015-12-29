package allstar.pms.domain;

import java.io.Serializable;

public class Member implements Serializable {
  private static final long serialVersionUID = 1L;
protected String id;
protected String pwd;
protected String name;
protected String email;
protected String tel;
protected String mphoto;
protected String zipcode;
protected String address;
protected int gender;
protected int age;
protected String mevent;          // 종목 변수
protected String introduce;


public String getId() {
  return id;
}
public void setId(String id) {
  this.id = id;
}
public String getPwd() {
  return pwd;
}
public void setPwd(String pwd) {
  this.pwd = pwd;
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
public String getMphoto() {
  return mphoto;
}
public void setMphoto(String mphoto) {
  this.mphoto = mphoto;
}
public String getZipcode() {
  return zipcode;
}
public void setZipcode(String zipcode) {
  this.zipcode = zipcode;
}
public String getAddress() {
  return address;
}
public void setAddress(String address) {
  this.address = address;
}
public int getGender() {
  return gender;
}
public void setGender(int gender) {
  this.gender = gender;
}
public int getAge() {
  return age;
}
public void setAge(int age) {
  this.age = age;
}
public String getMevent() {
  return mevent;
}
public void setMevent(String mevent) {
  this.mevent = mevent;
}
public String getIntroduce() {
  return introduce;
}
public void setIntroduce(String introduce) {
  this.introduce = introduce;
}

}
