<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 관리</title>
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
		사용자 목록
		<button style="margin-left: 100px"
			onclick="location.href='/jw/ch09/user/register'">회원가입</button>
		<!-- 로그인 x 시 보이는 창  -->
		<span style="font-size: 16px"> <c:if test="${empty sessUid}">
				<a style="margin-left: 100px" href="/jw/ch09/user/login">로그인</a>
			</c:if> 
			<!-- 로그인 시 보이는 창  -->
			<c:if test="${not empty sessUid}">
				<a style="margin-left: 100px" href="/jw/ch09/user/logout">로그아웃</a>
				<span style="margin-left: 30px">${sessUname}님 환영합니다</span>
			</c:if>
		</span>
	</h1>
	<hr>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>등록일</th>
			<th>액션</th>
		</tr>
		<c:forEach var="user" items="${list}">
			<tr>
				<td>${user.uid}</td>
				<td>${user.uname}</td>
				<td>${user.email}</td>
				<td>${user.regDate}</td>
				
				<!-- 본인만 수정 o, * 본인이다, 아니다에 따라 권한 다르게 부여-->
				<td><c:if test="${user.uid eq sessUid}">
						<a href="/jw/ch09/user/update?uid=${user.uid}">수정</a>
					</c:if> <c:if test="${user.uid ne sessUid}">
						<a href="#" disabled>수정</a>


						<!-- 본인 또는 관리자만 수정, 삭제 o -->
					</c:if> <c:if test="${(user.uid eq sessUid) or (sessUid eq 'admin')}">
						<a href="/jw/ch09/user/delete?uid=${user.uid}">삭제</a>
					</c:if> 
					<!--  ne: not / eq  같음,  드모르간 법칙 --> 
					<c:if
						test="${(user.uid ne sessUid) and (sessUid ne 'admin')}">
						<a href="#" disabled></a>
					</c:if></td>


			</tr>
		</c:forEach>

	</table>
	<br>

</body>
</html>