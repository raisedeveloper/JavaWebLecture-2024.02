<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현 언어 (EL)</title>
</head>
<body style="margin: 50px">
	<h1>컬렉션 (Collection)</h1>
	<hr>
	<table border="1">
		<tr>
			<th colspan="2">Array</th>
		</tr>
		<tr>
			<td>\${array[0]}</td>
			<td>${array[0]}</td>
		</tr>
		<tr>
			<td>\${array[3]}</td>
			<td>${array[3]}</td>
		</tr>
		<tr>
			<th colspan="2">List</th>
		</tr>
		<tr>
			<td>\${list[0]}</td>
			<td>${list[0]}</td>
		</tr>
		<tr>
			<td>\${list.get(1)}</td>
			<td>${list.get(1)}</td>
		</tr>
		<tr>
			<td>\${list[2]}</td>
			<td>${list[2]}</td>
		</tr>
		<tr>
			<th colspan="2">Map</th>
		</tr>
		<tr>
			<td>\${map.key}</td>
			<td>${map.key}</td>
		</tr>
		<tr>
			<td>\${map.value}</td>
			<td>${map.value}</td>
		</tr>
	</table>
</body>
</html>