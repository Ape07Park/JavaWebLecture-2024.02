<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*" %>

<c:set var="price" value="123456000"></c:set>
<c:set var="now" value="<%= new Date() %>" />


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
	
		<tr><th>표현방법</th><th>결과</th></tr>
		
		<tr><th colspan="2">숫자</th></tr>
		<tr><td>일반 숫자</td><td>${price}</td></tr>
		<tr><td>천단위 구분기호</td><td><fmt:formatNumber type="number" value="${price}"/></td></tr>
		<tr><td>통화(KRW)</td>
			<td><fmt:formatNumber type="currency" currencySymbol="KRW" value="${price}"/></td></tr>
			
		<tr><td>통화(USD)</td>
			<td><fmt:formatNumber type="currency" currencySymbol="USD" value="${price}"/></td></tr>
		
		
		<tr><td>퍼센트</td><td><fmt:formatNumber type="percent" value="${price / 100000000}"/></td></tr>
		
		
		<%-- 여기부터 복붙하기  --%>
		<tr><th colspan="2">날짜</th></tr>
		
	  <tr><td>일반 날짜</td><td>${now}</td></tr>
	  
      <tr><td>full date</td>
         <td><fmt:formatDate type="date" dateStyle="full" value="${now}" /></td></tr>
      <tr><td>short date</td>
         <td><fmt:formatDate type="date" dateStyle="short" value="${now}" /></td></tr>
      <tr><td>time</td>
         <td><fmt:formatDate type="time" value="${now}" /></td></tr>
      <tr><td>pattern("YYYY-MM-dd HH:mm:ss")</td>
         <td><fmt:formatDate pattern="YYYY-MM-dd HH:mm:ss" value="${now}" /></td></tr>
      <tr><td>pattern("YY-MM-dd HH:mm")</td>
         <td><fmt:formatDate pattern="YY-MM-dd HH:mm" value="${now}" /></td></tr>
		
		
	</table>	
	
</body>
</html>