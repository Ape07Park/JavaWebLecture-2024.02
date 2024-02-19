<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>노래 수정</title>
</head>
<body style="margin: 50px;">
	<h1>노래 수정</h1>
	<hr>
	 
	<form action="/jw/ch07/kpop/updateSong" method="post">
	
	    <%-- read Only를 위해 hidden(안보이게 전송), disabled(남이 수정 x) 필요 --%>
	   
		<input type="hidden" name="sid" value="${song.sid}"> <%-- DB에 전송 --%>
		<input type="text" value="${song.sid}" disabled> <br><br> <%-- 화면에 보임 --%> 
		
		<input type="text" name="title" value="${song.title}" placeholder="title"> <br><br>
		<input type="text" name="lyrics" value="${song.lyrics}" placeholder="lyrics"> <br><br> 
		
		
			
		<input type="submit" value="수정">

	</form>
</body>
</html>