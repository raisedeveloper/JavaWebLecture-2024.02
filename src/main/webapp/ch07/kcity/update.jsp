<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>도시 수정</title>
</head>
<body>
	<h1>도시 수정</h1>
	<hr>
	<form action="/jw/ch07/kcity/update" method="post">
		<!-- 사람에게는 안보이지만 정보는 전달해주는 hidden tag (disabled 정보수정방지)-->
		<input type="hidden" name="id" value="${city.id}"> 
		<input type="text" name="id" value="${city.id}" disabled><br></br>
		<input type="text" name="name" value="${city.name}"><br></br>
		<input type="text" name="countryCode" value="${city.countryCode}"><br></br>
		<!-- <input type="text" name="district" value="${city.district}"><br></br> -->
		<select name="district">
			<c:forEach var="dist" items="${districts}">
				<option value="${dist}" ${dist eq city.district ? "selected" : ""}>${dist}</option>			
			</c:forEach>
		</select><br></br>
		<input type="text" name="population" value="${city.population}"><br></br>
		<input type="submit" value="제출">
	</form>
</body>
</html>