<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
pageContext.setAttribute("pid", "페이지 변수");
%>
<c:set var="cid1" value="코어 변수1"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL</title>
<style>
td, th {
   padding: 3px;
}
</style>
</head>
<body style="margin: 50px">
   <h1>JSP Standard Tag Library (JSTL) - Core</h1>
   <c:set var="cid2" value="코어 변수2"></c:set>
   <hr>
   <table border="1">
      <tr>
         <th>표현식</th>
         <th>결과</th>
      </tr>
      <tr>
         <td>\${pid}</td>
         <td>${pid}</td>
      </tr>
      <tr>
         <td>\${cid1}</td>
         <td>${cid1}</td>
      </tr>
      <tr>
         <td>\${cid2}</td>
         <td>${cid2}</td>
      </tr>
      <tr>
         <th colspan="2">Array 중 하나의 원소를 새로운 변수로 지정할 수 있다.</th>
      </tr>
      <tr>
         <td>\${members[0].id}</td>
         <td>${members[0].id}</td>
      </tr>
      <tr>
         <td>\${members[0].name}</td>
         <td>${members[0].name}</td>
      </tr>
      <tr>
         <td>\${members[0].addr}</td>
         <td>${members[0].addr}</td>
      </tr>
      <c:set var="member" value="${members[0]}"></c:set>
      <tr>
         <td>\${member.id}</td>
         <td>${member.id}</td>
      </tr>
      <tr>
         <td>\${member.name}</td>
         <td>${member.name}</td>
      </tr>
      <tr>
         <td>\${member.addr.city}</td>
         <td>${member.addr.city}</td>
      </tr>
   </table>
   <%-- 아이디 열: 아이디가 홀수이면 글자색을 blue, 짝수면 red --%>
   <%-- 이름 열: 첫줄 배경색은 yellow, 마지막줄을 cyan --%>
   <%-- 주소 열: 한국이면 배경색을 orange, 미국이면 배경색을 skyblue --%>
   <table border="1">
      <tr>
         <th>아이디</th>
         <th>이름</th>
         <th>주소</th>
         <th>인덱스</th>
      </tr>
      <c:forEach var="member" items="${members}" varStatus="loop">
         <tr>
            <td>
               <c:if test="${member.id % 2 == 1}">
                  <span style="color: blue;">${member.id}</span>
               </c:if> 
               <c:if test="${member.id % 2 == 0}">
                  <span style="color: red;">${member.id}</span>
               </c:if>
            </td>
            <td>
               <c:if test="${loop.first}">
                  <span style="background-color: yellow;">${member.name}</span>
               </c:if> 
               <c:if test="${loop.last}">
                  <span style="background-color: cyan;">${member.name}</span>
               </c:if> 
               <c:if test="${not(loop.last or loop.first)}">
                  ${member.name}
               </c:if> 
            </td>
            <td>
               <c:if test="${member.addr.country eq '한국'}">
                  <span style="background-color: orange;">${member.addr.country}</span>
               </c:if> 
               <c:if test="${member.addr.country eq '미국'}">
                  <span style="background-color: skyblue;">${member.addr.country}</span>
               </c:if> 
               <c:if test="${member.addr.country ne '미국' and member.addr.country ne '한국'}">
                  ${member.addr.country}
               </c:if> 
            </td>
            <td>
            ${loop.index}
            </td>
            
         </tr>
      </c:forEach>
   </table>
</body>
</html>