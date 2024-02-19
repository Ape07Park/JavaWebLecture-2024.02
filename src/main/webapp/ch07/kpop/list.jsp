<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- core, fmt(날짜), function(String 관련 함수) -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>k-pop 아티스트들</title>
<style>
th {
	padding: 3px;
	text-align: center;
}

td {
	padding: 3px;
}
</style>
</head>
<body style="margin: 50px;">
	<h1>
		k-pop 아티스트 list

		<button style="background-color: powderblue;"
			"margin-left:140px";  onclick="location.href='/jw/ch07/kpop/insertSong'">노래
			추가</button>
		<button style="background-color: powderblue;"
			"margin-left:140px"; onclick="location.href='/jw/ch07/kpop/insertArtist'">걸그룹
			추가</button>
	</h1>
	<hr>
	<table border="margin: 50px;">

		<tr>
			<th>Aid</th>
			<th>Name</th>
			<th>debutDate</th>
			<th>title</th>
			<th>lyrics</th>
			<th>삭제</th>

		</tr>
		<!-- jst core로 반복문 o  -->

		<c:forEach var="kpop" items="${list}">
			<!-- for (City city: list) ${list}니 서버가 값 줌 이 표현들은 외우지말고 메뉴얼 보고 치기-->

			<tr>
				<td>${kpop.aid}</td>
				<!-- city class의 필드와 동일해야 함 -->
				<td><a href="/jw/ch07/kpop/updateArtist?aid=${kpop.aid}">${kpop.name}</a></td>

				<td>${kpop.debutDate}</td>

				<td><a href="/jw/ch07/kpop/updateSong?sid=${kpop.sid}">${kpop.title}</a></td>

				<td>${kpop.lyrics}</td>
				
				<!-- delete를 -> deleteArtist -->
				<td><a href="/jw/ch07/kpop/deleteArtist?aid=${kpop.aid}">걸그룹</a> <!-- * 이건 알아두기 id 수정 -->
					<a href="/jw/ch07/kpop/deleteSong?sid=${kpop.sid}">노래</a></td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>