<%-- 게시물 상세정보 및 변경 폼 --%>
<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
  <meta charset='UTF-8'>
  <title>게시판-상세정보</title>
  <link rel="stylesheet" type="text/css" href="../css/common.css">
</head>
<body>

<jsp:include page="/Header.jsp"/>

<h1>대회정보</h1>

<c:if test="${not empty competition}">
<form id='form1' action='update.do' method='post'>
<table border='1'>
<tr>
  <th>번호</th>
  <td><input type='text' name='no' value='${competition.no}' readonly></td>
</tr>
<tr>
  <th>대회명</th>
  <td><input type='text' name='name' value='${competition.name}'></td>
</tr>
<tr>
  <th>이벤트</th>
  <td><input type='text' name='event' value='${competition.event}'></td>
</tr>
<tr>
  <th>개최지</th>
  <td><input type='text' name='venue' value='${competition.venue}'></td>
</tr>
<tr>
  <th>팀숫자</th>
  <td><input type='text' name='teamNum' value='${competition.teamNum}'></td>
</tr>
<tr>
  <th>참가비</th>
  <td><input type='text' name='joinCost' value='${competition.joinCost}'></td>
</tr>
<tr>
  <th>대회시작일</th>
  <td><input type='date' name='startDate' value='${competition.startDate}'></td>
</tr>
<tr>
  <th>대회종료일</th>
  <td><input type='date' name='endDate' value='${competition.endDate}'></td>
</tr>
<tr>
  <th>모집시작일</th>
  <td><input type='date' name='recruitStartDate' value='${competition.recruitStartDate}'></td>
</tr>
<tr>
  <th>모집마감일</th>
  <td><input type='date' name='recruitEndDate' value='${competition.recruitEndDate}'></td>
</tr>
<tr>
  <th>내용</th>
  <td><textarea rows='10' name='content' 
      cols='60'>${competition.content}</textarea></td>
</tr>
<tr>
  <th>개최자아이디</th>
  <td><input type='text' name='hostId' value='${competition.hostId}'></td>
</tr>

</table>
<p>
<button name='update' type='submit' class='button1'>변경</button>
<a id='aDelete' href='delete.do?no=${competition.no}' class='button2' onclick='deleteBoard()'>삭제</a>
</p>
</form>
</c:if>

<c:if test="${empty board}">
<p>해당 번호의 게시물을 찾을 수 없습니다.</p>
</c:if>

<jsp:include page="/Copyright.jsp"/>
<script>
function deleteBoard() {
	// 암호 텍스트 상자에 입력된 내용을 가져온다.
	var password = document.getElementById('inputPassword').value;
	
	// a 태그의 href 값을 가져와서 "&password=암호" 문자열을 붙인다.
	var href = document.getElementById('aDelete').href 
	           + "&password=" + password;
	
	// a 태그의 href 값을 암호 파라미터가 붙은 값으로 변경한다. 
	document.getElementById('aDelete').href = href;
}
</script>
</body>
</html>
    