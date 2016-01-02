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
<a href='add.do'>새 회원</a><br>
<table border='1'>
  <tr>
    <th>아이디</th>
    <th>이름</th>
    <th>이메일</th>
    <th>전화</th>
    <th>사진</th>
    <th>우편번호</th>
    <th>상세주소</th>
    <th>성별</th>
    <th>나이</th>
    <th>종목</th>
    <th>자기소개</th>

  </tr>
<c:forEach var="member" items="${members}">
  <tr>
    <td>${member.id}</td> 
    <td><a href='detail.do?id=${member.id}'>${member.name}</a></td>
    <td>${member.email}</td>
    <td>${member.tel}</td>
    <td>${member.mphoto}</td>
    <td>${member.zipcode}</td>
    <td>${member.address}</td>
    <td>${member.gender}</td>
    <td>${member.age}</td>
    <td>${member.mevent}</td>
    <td>${member.introduce}</td>

  </tr>
</c:forEach>  
</table>


</body>
</html>





    