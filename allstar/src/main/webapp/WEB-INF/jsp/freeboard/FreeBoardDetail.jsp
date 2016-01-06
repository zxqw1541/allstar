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

<h1>자유게시물 정보</h1>

<c:if test="${not empty freeBoard}">
<form id='form1' action='update.do' method='post'
      enctype="multipart/form-data">
<table border='1'>
<tr>
  <th>번호</th>
  <td><input type='text' name='fno' value='${freeBoard.fno}' readonly></td>
</tr>
<tr>
  <th>제목</th>
  <td><input type='text' name='title' value='${freeBoard.title}'></td>
</tr>
<tr>
  <th>내용</th>
  <td><textarea rows='10' name='content' 
      cols='60'>${freeBoard.content}</textarea></td>
</tr>
<tr>
  <th>조회수</th>
  <td>${freeBoard.views}</td>
</tr>
<tr>
  <th>등록일</th>
  <td>${freeBoard.createdDate}</td>
</tr>
</table>
<p>
<button name='update' type='submit' class='button1'>변경</button>
<a id='aDelete' href='delete.do?no=${freeBoard.fno}' class='button2'>삭제</a>
</p>
</form>
</c:if>

<c:if test="${empty freeBoard}">
<p>해당 번호의 게시물을 찾을 수 없습니다.</p>
</c:if>

<jsp:include page="/Copyright.jsp"/>
<script>
function deleteFreeBoard() {
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
    