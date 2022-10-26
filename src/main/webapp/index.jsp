<%@page import="java.text.SimpleDateFormat"%>
<%@page import="util.MathUtil"%>
<%@page import="java.util.*"%>
<%@page import="domain.Wifi"%>
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
		WifiInfoService wifiInfoService = null;
		List<Wifi> nearbyWifi = new ArrayList<Wifi>();
		Double curLatitude = null;
		Double curLongitude = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		if (request.getParameter("latitude") != null && request.getParameter("longitude") != null) {
			curLatitude = Double.parseDouble(request.getParameter("latitude"));
			curLongitude = Double.parseDouble(request.getParameter("longitude"));
			wifiInfoService = new WifiInfoService();
			nearbyWifi = wifiInfoService.getNearbyWifiList(curLatitude, curLongitude);
		}
	%>
	<h1>와이파이 정보 구하기</h1>
	<ul>
		<li><a href="/">홈</a></li>
		<li><a href="/history.jsp">위치 히스토리 목록</a></li>
		<li><a href="/load-wifi.jsp">Open API 와이파이 정보 가져오기</a></li>
	</ul>
	<form action="/">
		<label for="latitude">LAT:</label>
		<input type="text" id="latitude" name="latitude" value="<%=curLatitude == null ? 0 : curLatitude %>" />
		<label for="longitude">LNT:</label>
		<input type="text" id="longitude" name="longitude" value="<%=curLongitude == null ? 0 : curLongitude %>" />
		<button type="button" id="position-btn">내 위치 가져오기</button>
		<button type="submit">근처 WIFI 정보 보기</button>
	</form>
	<table>
		<thead>
			<tr>
				<th>거리(Km)</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>WIFI접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>작업일자</th>
			</tr>
		</thead>
		<tbody>
			<% for (Wifi wifi : nearbyWifi) { %>
			<tr>
				<td><%=String.format("%.4f", MathUtil.calculateDistance(curLatitude, curLongitude, wifi.getLatitude(), wifi.getLongitude())) %></td>
				<td><%=wifi.getManagementNumber()%></td>
				<td><%=wifi.getDistrict() %></td>
				<td><%=wifi.getName() %></td>
				<td><%=wifi.getAddress1() %></td>
				<td><%=wifi.getAddress2() %></td>
				<td><%=wifi.getInstalledFloor() %></td>
				<td><%=wifi.getInstallationType() %></td>
				<td><%=wifi.getInstallationAgency() %></td>
				<td><%=wifi.getServiceClassification() %></td>
				<td><%=wifi.getMeshType() %></td>
				<td><%=wifi.getInstallationYear() %></td>
				<td><%=wifi.getInOutDoor() %></td>
				<td><%=wifi.getConnectionEnvironment() %></td>
				<td><%=wifi.getLatitude() %></td>
				<td><%=wifi.getLongitude() %></td>
				<td><%=simpleDateFormat.format(wifi.getWorkDate()) %></td>
			</tr>		
			<% } %>
		</tbody>
	</table>
</body>
<script>
	const positionBtn = document.querySelector('#position-btn');
	positionBtn.addEventListener('click', event => {
		const latitudeInput = document.querySelector('#latitude');
		const longitudeInput = document.querySelector('#longitude');
		navigator.geolocation.getCurrentPosition((position) => {
			latitudeInput.value = position.coords.latitude;
			longitudeInput.value = position.coords.longitude;
		});
	});
</script>
</html>