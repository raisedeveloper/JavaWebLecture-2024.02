<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>회원 가입</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/f474084c1e.js"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<style>
* {
	font-family: 'Noto Sans KR', sans-serif;
}

a {
	text-decoration: none;
}

td {
	text-align: center;
}

img.sns {
	height: 32px;
}
</style>
</head>

<body>
	<div class="container mt-3">
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
			<div class="container-fluid">
				<img src="/jw/img/ck-logo.png" height="60">
				<div class="p-2 bg-dard justify-content-center">
					<img src="https://picsum.photos/1500/180" width="600">
				</div>
			</div>
		</nav>
		<div class="row" style="margin: 100px;">
			<div class="col-2"></div>
			<div class="col-8">
				<div class="card">
					<div class="card-body">
						<div class="card-title">
							<h2>
								<strong>회원 가입</strong>
							</h2>
							<p>아래의 항목을 입력하세요.</p>
							<hr>
						</div>
						<form action="/jw/ch06/register" method="post">
							<table class="table table-borderless">
								<tb>
								<tr>
									<td><label for="id" class="col-form-label">아이디:</label></td>
									<td><input type="text" class="form-control" id="id" name="uid"
										placeholder="아이디를 입력하세요."></td>
								</tr>
								<tr>
									<td><label for="pwd1" class="col-form-label">패스워드:</label></td>
									<!--col-form-label 필수!-->
									<td><input type="password" class="form-control" id="pwd1" name="pwd"
										placeholder="패스워드를 입력하세요."></td>
								</tr>
								<tr>
									<td><label for="pwd2" class="col-form-label">패스워드 확인:</label></td>
									<!--col-form-label 필수!-->
									<td><input type="password" class="form-control" id="pwd2" name="pwd2"
										placeholder="패스워드를 다시 입력하세요."></td>
								</tr>
								<tr>
									<td><label for="urname" class="col-form-label">이름:</label></td>
									<td><input type="text" class="form-control" id="urname" name="name"
										placeholder="이름을 입력하세요."></td>
								</tr>
								<tr>
									<td><label for="email" class="col-form-label">이메일:</label></td>
									<td><input type="email" class="form-control" id="email" name="email"
										placeholder="이메일을 입력하세요."></td>
								</tr>
								<tr>
									<td colspan="2">
										<button type="submit" class="btn btn-primary mt-3">제출</button>
										<button type="reset" class="btn btn-secondary mt-3">취소</button>
									</td>
								</tr>
								</tb>
							</table>
						</form>
						
						<p class="mt-3">
							<span class="me-3">이미 사용자 계정이 있으신가요?</span> <a href="/jw/ch06/login">
								로그인 </a>
						</p>
						
						<p class="mt-3 mb-3">
							<span class="me-3">소셜 계정으로 가입</span> <span> <a href="#"><img
									class="sns" src="/jw/img/google-logo.png">구글</a> <a href="#"><img
									class="sns" src="/jw/img/github-logo.png">깃허브</a> <a href="#"><img
									class="sns" src="/jw/img/naver-logo.jpg">네이버</a> <a href="#"><img
									class="sns" src="/jw/img/kakao-logo.png">카카오</a>
							</span>
						</p>
						
						<div class="mt-3"></div>
					</div>
				</div>
				<div class="col-2"></div>
			</div>
		</div>
	</div>
</body>


</html>