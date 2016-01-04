<%-- EL을 이용하여 게시물 데이터 출력하기 --%>
<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
  <meta charset='UTF-8'>
  <title>대회-목록</title>
</head>
<body>

<jsp:include page="/Header.jsp"/>

<h1>대회</h1>
<a href='add.do'>새 대회</a><br>
<table border='1'>
  <tr>
    <th>번호</th>
    <th>대회명</th>
    <th>타입</th>
    <th>이벤트</th>
    <th>개최지</th>
    <th>팀숫자</th>
    <th>참가비</th>
    <th>대회시작일</th>
    <th>대회종료일</th>
    <th>모집시작일</th>
    <th>모집마감일</th>
    <th>내용</th>
    <th>개최자아이디</th>
  </tr>
<c:forEach var="competition" items="${competitions}">
  <tr>
    <td>${competition.no}</td> 
    <td><a href='detail.do?no=${competition.no}'>${competition.name}</a></td>
    <td>${competition.type}</td>
    <td>${competition.event}</td>
    <td>${competition.venue}</td>
    <td>${competition.teamNum}</td>
    <td>${competition.joinCost}</td>
    <td>${competition.startDate}</td>
    <td>${competition.endDate}</td>
    <td>${competition.recruitStartDate}</td>
    <td>${competition.recruitEndDate}</td>
    <td>${competition.content}</td>
    <td>${competition.hostId}</td>
  </tr>
</c:forEach>  
</table>

<jsp:include page="/Copyright.jsp"/>

</body>
</html>





    