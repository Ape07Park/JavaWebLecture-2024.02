<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도시 수정</title>
</head>
<body style="margin: 50px;">
	<h1>도시 수정</h1>
	<hr>
	 <%-- action 잘 고치기 왜냐하면 "/jw/ch07/city/insert로 해서 데이터라 거기로 넘어감" --%>
	<form action="/jw/ch07/kcity/update" method="post">
	
	    <%-- read Only를 위해 hidden(안보이게 전송), disabled(남이 수정 x) 필요 --%>
	   
		<input type="hidden" name="id" value="${city.id}"> <%-- DB에 전송 --%>
		<input type="text" name="id" value="${city.id}" disabled> <br><br> <%-- 화면에 보임 --%> 
		
		<input type="text" name="name" value="${city.name}"> <br><br>
		<input type="text" name="countryCode" value="${city.countryCode}"> <br><br> <%-- default 값을 KOR로 한 거랑 같은 효과 --%>
		
		<!--<input type="text" name="district" value="${city.district}"><br><br>  -->
		<select name="district">
			<c:forEach var="dist" items="${districts}">
				<option value="${dist}" ${dist eq city.district ? "selected" : ""}>${dist}</option>
			</c:forEach>
		</select>
		<br>
		<br>
		
		<input type="text" name="population" value="${city.population}"> <br><br>
			
		<input type="submit" value="제출">

	</form>
</body>
</html>