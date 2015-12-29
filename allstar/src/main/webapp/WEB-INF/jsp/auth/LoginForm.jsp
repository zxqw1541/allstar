<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<h1>로그인</h1>
<form action="login.do" method="post">
아이디: <input type="text" name="id" value="${cookie.id.value}"><br>
암호: <input type="pwd" name="pwd"><br>
<input type="submit" value="로그인"><br>
<input type="checkbox" name="saveId" 
  ${(empty cookie.id)?"":"checked"}> 아이디 저장<br>
</form>
</body>
</html>





