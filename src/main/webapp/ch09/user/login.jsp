<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>사용자 관리</title>
</head>
<body style="margin: 50px">
	<h1>로그인</h1>
		<button style="margin-left: 100px" onclick="location.href='/jw/ch09/user/register'">회원가입</button>
	<hr>
	<form action="/jw/ch09/user/login" method="post">
		<input type="text" name="uid" placeholder="사용자 아이디"><br><br>
		<input type="password" name="pwd" placeholder="패스워드"><br><br>
		<input type="submit" value="로그인">
		
	</form>
</body>
</html>