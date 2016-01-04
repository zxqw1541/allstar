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
  <th>대회명</th>
  <td><input type='text' name='name' placeholder="대회명을 넣으세요."></td>
</tr>
<tr>
  <th>이벤트</th>
  <td><input type='text' name='event' placeholder="대회명을 넣으세요."></td>
</tr>
<tr>
  <th>개최지</th>
  <td><input type='text' name='venue' placeholder="대회명을 넣으세요."></td>
</tr>
<tr>
  <th>팀숫자</th>
  <td><input type='text' name='teamNum' placeholder="대회명을 넣으세요."></td>
</tr>
<tr>
  <th>참가비</th>
  <td><input type='text' name='joinCost' placeholder="대회명을 넣으세요."></td>
</tr>
<tr>
  <th>대회시작일</th>
  <td><input type='date' name='startDate'></td>
</tr>
<tr>
  <th>대회종료일</th>
  <td><input type='date' name='endDate'></td>
</tr>
<tr>
  <th>모집시작일</th>
  <td><input type="date" name='recruitStartDate'></td>
</tr>
<tr>
  <th>모집마감일</th>
  <td><input type='date' name='recruitEndDate'></td>
</tr>
<tr>
  <th>내용</th>
  <td><textarea rows='10' name='content' cols='60'
       placeholder="내용을 입력하세요."></textarea></td>
</tr>
<tr>
  <th>개최자아이디</th>
  <td><input type='text' name='name' placeholder="대회명을 넣으세요."></td>
</tr>

</table>

<p><button type='submit'>등록</button></p>

</form>

<jsp:include page="/Copyright.jsp"/>

</body>
</html>
