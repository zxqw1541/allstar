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
  <title>종목 게시판-상세정보</title>
  <link rel="stylesheet" type="text/css" href="../css/common.css">
</head>
<body>

<jsp:include page="/Header.jsp"/>

<h1>게시물 정보(with JSP + EL + JSTL)</h1>

<c:if test="${not empty board}">
<form id='form1' action='update.do' method='post'>
<table border='1'>
<tr>
  <th>번호</th>
  <td><input type='text' name='no' value='${board.bno}' readonly></td>
</tr>
<tr>
  <th>종목</th>
  <td><input type='text' name='bevent' value='${board.bevent}'></td>
</tr>
<tr>
  <th>제목</th>
  <td><input type='text' name='title' value='${board.title}'></td>
</tr>
<tr>
  <th>내용</th>
  <td><textarea rows='10' name='content' 
      cols='60'>${board.content}</textarea></td>
</tr>
<tr>
  <th>등록일</th>
  <td>${board.cre_dt}</td>
</tr>
</table>
<p>
<button name='update' type='submit' class='button1'>변경</button>
<a href='delete.do?bno=${board.bno}' class='button2'>삭제</a>
</p>
</form>
</c:if>

<c:if test="${empty board}">
<p>해당 번호의 게시물을 찾을 수 없습니다.</p>
</c:if>

<jsp:include page="/Copyright.jsp"/>
</body>
</html>
    