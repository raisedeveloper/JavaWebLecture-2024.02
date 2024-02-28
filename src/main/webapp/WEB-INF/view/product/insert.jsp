<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert</title>
</head>
<body style="margin: 50px">
	<h1>상품 입력</h1>
	
	<hr>
	<!-- 파일 업로드 시 getter setter 사용 불가 post만 사용 -->
	<form action="/jw/bbs/product/insert" method="post" enctype="multipart/form-data"> <!-- 파일업로드 공식 -->
		<%-- 파일 업로드 시 인코딩 타입 변경 --%>
		<%-- 카테고리 리스트 내려받음 --%>
		<select name="category">
			<option value="스키" selected>스키</option>
			<option value="보드">보드</option>
			<option value="부츠">부츠</option>
		</select><br><br>
		<input type="text" name="pname" placeholder="상품명"><br><br>
		<input type="text" name="price" placeholder="가격"><br><br>
		<textarea rows="3" cols="40" name="description"></textarea><br><br>
		
		<%-- img 파일 업로드 칸 --%>
		<input type="file" name="imgFile" multiple><br><br>
		
		<input type="submit" value="확인">
	</form>
</body>
</html>