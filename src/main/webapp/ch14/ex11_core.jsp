<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 자바 코드 --%>
<%
	pageContext.setAttribute("pid", "페이지 변수");
%>
<%-- pid 대신 사용 --%>
<c:set var="cid1" value="코어 변수1"></c:set>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSTL(JavaServer Pages Standard Tag Library)</title>
	<style>
		td, th{ padding: 3px; }
	</style>
</head>
<!-- 요청 들어오면 컴파일함 -->
<body style= "margin: 50px; margin-bottom: 100px">
	<h1>JSP Standard Tag Library(JSTL) -core</h1>
	<c:set var="cid2" value="코어 변수2"></c:set>
	<hr>
	<table border="1">
	
		<tr><th>표현식</th><th>결과</th></tr>
		
		<tr><td>\${pid}</td><td>${pid}</td></tr>
		<tr><td>\${cid1}</td><td>${cid1}</td></tr>
		<tr><td>\${cid2}</td><td>${cid2}</td></tr>
		
		<tr><th colspan="2">어레이의 한 엘리멘트를 새로운 변수로 </th></tr>
		
		<tr><td>\${members[0].id}</td><td>${members[0].id}</td></tr>
		<tr><td>\${members[0].name}</td><td>${members[0].name}</td></tr>
		<tr><td>\${members[0].addr}</td><td>${members[0].addr}</td></tr>
		
		
		<c:set var="member" value="${members[0]}"></c:set>
		<tr><td>\${member.id}</td><td>${member.id}</td></tr>
		<tr><td>\${member.name}</td><td>${member.name}</td></tr>
		<tr><td>\${member.addr.city}</td><td>${member.addr.city}</td></tr>
	</table>	
	
	
	<%-- 아이디 열: 아이디가 홀수면 글자색 blue, 짝수면 red --%>
	<%-- 이름 열: 첫번째 줄 배경색은 yellow, 마지막 줄을 cyan --%>
	<%-- 주소 열: 한국이면 배경색을 orange, 미국이면 skyblue --%>
	<table border = "1">
	 <tr>
	 	<th>아이디</th>
	 	<th>이름</th>
	 	<th>주소</th>
	 </tr>
	 <br>
	 	<c:forEach var="member" items="${members}" varStatus="loop">
	 		<tr>
	 			<td><%-- 아이디 열: 아이디가 홀수면 글자색 blue, 짝수면 red --%>
	 				<c:if test="${member.id % 2 == 1 }">
	 					<span style="color:blue">${member.id}</span>
	 				</c:if>
	 				
	 				<c:if test="${member.id % 2 == 0 }">
	 					<span style="color:red">${member.id}</span>
	 				</c:if>
	 			</td>
	 			
	 			<td><%-- 이름 열: 첫번째 줄 배경색은 yellow, 마지막 줄을 cyan --%>
	 				<c:if test="${loop.first}">
	 					<span style="background: yellow">${member.name}</span>
	 				</c:if>
	 				
	 				<c:if test="${loop.last}">
	 					<span style="background:cyan">${member.name}</span>
	 				</c:if>
	 				
	 				<%-- 이름 열: 첫번째 줄 ,마지막 줄 x 나머지 --%>
	 				<c:if test="${not loop.last or loop.first}">
	 					${member.name}
	 				</c:if>
	 				
	 			</td>
	 			
	 			<td><%-- 주소 열: 한국이면 배경색을 orange, 미국이면 skyblue --%>
	 				<%-- " 이미 사용해서 문자열 표시는 ' 로 함 --%>
	 				<c:if test="${member.addr.country eq '한국'}">
	 					<span style="background:orange">${member.addr}</span>
	 				</c:if>
	 				<c:if test="${member.addr.country eq '미국'}">
	 					<span style="background:skyblue">${member.addr}</span>
	 				</c:if>
	 				<c:if test="${member.addr.country ne '한국' and member.addr.country ne '미국'}">
	 					${member.addr}
	 				</c:if>
	 			</td>
	 			<td>
	 				${loop.index}
	 			</td>
	 		</tr>
	 		
	 	</c:forEach>
	</table>
		


</body>
</html>