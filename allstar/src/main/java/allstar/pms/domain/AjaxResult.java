package allstar.pms.domain;

public class AjaxResult {
protected String status;
protected Object data;

public AjaxResult() {
}

public AjaxResult(String status, Object data) {
  this.status = status;
  this.data = data;
}

public String getStatus() {
  return status;
}

public void setStatus(String status) {
  this.status = status;
}

public Object getData() {
  return data;
}

public AjaxResult setData(Object data) {
  this.data = data;
  return this;
}





}
