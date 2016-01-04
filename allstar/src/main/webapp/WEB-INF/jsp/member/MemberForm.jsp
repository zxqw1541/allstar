<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset='UTF-8'>
  <title>대회 - 새 대회</title>
</head>
<body>
<h1>새 대회</h1>
<form action='add.do' method='post'>
<table border='1'>
<tr>
  <th>이름</th>
  <td><input type='text' name='name' ></td>
</tr>
<tr>
  <th>아이디</th>
  <td><input type='text' name='id' ></td>
</tr>
<tr>
  <th>비밀번호</th>
  <td><input type='password' name='pwd' ></td>
</tr>

<tr>
  <th>이메일</th>
  <td><input type='email' name='email' ></td>
</tr>
<tr>
  <th>전화</th>
  <td><input type='text' name='tel' ></td>
</tr>
<tr>
  <th>사진</th>
  <td><input type='text' name='mphoto' ></td>
</tr>
<tr>
  <th>우편번호</th>
  <td><input type='text' name='zipcode'></td>
</tr>
<tr>
  <th>상세주소</th>
  <td><input type='text' name='address'></td>
</tr>
<tr>
  <th>성별</th>
  <td><input type="text" name='gender'></td>
</tr>
<tr>
  <th>나이</th>
  <td><input type='text' name='age'></td>
</tr>
<tr>
  <th>내용</th>
  <td><textarea rows='10' name='introduce' cols='60'
       placeholder="내용을 입력하세요."></textarea></td>
</tr>
<tr>
  <th>종목</th>
  <td><textarea rows='10' name='mevent' cols='60'
       placeholder="내용을 입력하세요."></textarea></td>
</tr>


</table>

<p><button type='submit'>등록</button></p>

</form>

<jsp:include page="/Copyright.jsp"/>

</body>
</html>
