<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%-- 입력을 받음 프론트 단 --%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>아티스트 추가</title>
</head>
<body style="margin: 50px;">
	<h1>아티스트 추가</h1>
	<hr>
	<form action="/jw/ch07/kpop/insertArtist" method="post">
		<input type="text" name="name" placeholder="그룹 이름"> <br><br>
		
		<!--  debut랑 이름 안맞음  -->
		<input type="text" name="debut" placeholder="데뷔일"> <br><br>
		
		<input type="text" name="songId" placeholder="히트송 아이디"> <br><br>
		
		<!-- sid ㅇ안받음  -->
		
		<input type="submit" value="추가">

	</form>
</body>
</html>