<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아티스트 수정</title>
</head>
<body style="margin: 50px;">
	<h1>아티스트 수정</h1>
	<hr>
	 
	<form action="/jw/ch07/kpop/updateArtist" method="post">
	
	    <!-- read Only를 위해 hidden(안보이게 전송), disabled(남이 수정 x) 필요 -->
	   
		<input type="hidden" name="aid" value="${artist.aid}"> <%-- DB에 전송 --%>
		<input type="text" value="${artist.aid}" disabled> <br><br> <%-- 화면에 보임 --%> 
		
		<input type="text" name="name" value="${artist.name}" placeholder="name"> <br><br>
		<input type="text" name="debut" value="${artist.debut}" placeholder="debut"> <br><br> 
		
		
		<input type="text" name="songId" value="${artist.hitSongId}"> <br><br>
			
		<input type="submit" value="수정">

	</form>
</body>
</html>