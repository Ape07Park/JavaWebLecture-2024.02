<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%-- 입력을 받음 프론트 단 --%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>노래 추가</title>
</head>
<body style="margin: 50px;">
	<h1>노래 추가</h1>
	<hr>
	<form action="/jw/ch07/kpop/insertSong" method="post">
		<!-- value="${song.title}" 무슨 의미? -->
		<input type="text" name="title"  value="${song.title}"> <br><br>
		
		<input type="text" name="lyrics"  value="${song.lyrics}"> <br><br>
	
		
		<input type="submit" value="추가">

	</form>
</body>
</html>