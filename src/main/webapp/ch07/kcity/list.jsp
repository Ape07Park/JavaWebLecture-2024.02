<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!-- core, fmt(날짜), function(String 관련 함수) -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>국내 도시</title>
<style>
	th, td { padding: 3px;}
</style>
</head>
<body style="margin:50px;">
	<h1>국내 도시 list
	
	<button style="margin-left:100px" onclick="location.href='/jw/ch07/kcity/insert'">추가</button>
	</h1>
	<hr>
	<table border="1">
	
		<tr>
		<th>ID</th>
		<th>Name</th>
		<th>국가코드</th>
		<th>지역명</th>
		<th>인구수</th>
		<th>액션</th>
		</tr>
		<!-- jst core로 반복문 o  -->
		
		<c:forEach var="city" items="${list}"> <!-- for (City city: list) ${list}니 서버가 값 줌 이 표현들은 외우지말고 메뉴얼 보고 치기--> 
		
		<tr>
			<td>${city.id}</td> <!-- city class의 필드와 동일해야 함 -->
			<td>${city.name}</td>
			<td>${city.countryCode}</td>
			<td>${city.district}</td>
			<td>${city.population}</td>
			
			<td>
				<a href="/jw/ch07/kcity/update?id=${city.id}">수정</a> <!-- * 이건 알아두기 id 수정 -->
				<a href="/jw/ch07/kcity/delete?id=${city.id}">삭제</a>
			</td>
		</tr>
		</c:forEach>
	
	</table>

</body>
</html>