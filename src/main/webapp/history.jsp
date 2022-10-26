<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="domain.WifiSearchHistory"%>
<%@page import="service.WifiInfoService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table {
		width: 100%
	}
	table, th, td {
		border: solid 1px;
	}
	td {
		text-align: center;
	}
	thead {
		background-color: green;
		color: white;
	}
	
	thead > tr {
		height: 3em;
	}
	
	ul {
		padding-left: 0px;
		margin-left: 0px;
		display: flex;
		list-style: none;
	}
	
	li {
		padding-right: 10px;
	}
</style>
</head>
<body>
	<% 
		WifiInfoService wifiInfoService = new WifiInfoService();
		List<WifiSearchHistory> histories = wifiInfoService.getAllWifiSearchHistory();
	%>
	<h1>위치 히스토리 목록</h1>
	<ul>
		<li><a href="/">홈</a></li>
		<li><a href="/history.jsp"> 위치 히스토리 목록</a></li>
		<li><a href="/load-wifi.jsp">Open API 와이파이 정보 가져오기</a></li>
	</ul>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<% for (int i = histories.size() - 1; i >= 0; i--) { 
				WifiSearchHistory history = histories.get(i);
			%>
			<tr>
				<td><%=history.getId()%></td>
				<td><%=history.getLatitude()%></td>
				<td><%=history.getLongitude()%></td>
				<td><%=history.getLookupDate() %></td>
				<td><button id=<%=history.getId() %>>삭제</button></td>
			</tr>		
			<% } %>
		</tbody>
	</table>
</body>
</html>