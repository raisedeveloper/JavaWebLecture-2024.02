<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <%@ include file="../common/_head.jspf"%>
	<style>
		td, th {text-align: center;}
		.disabled-link{pointer-events: none;}
	</style>
</head>
<body>
   <%@ include file="../common/_top.jspf"%>

   <div class="container" style="margin-top: 80px">
      <div class="row">
         <%@ include file="../common/_aside.jspf"%>
         <div class="col-9">
            <h3><strong class="me-5">사용자 목록</strong>
               <span style="font-size: 16px;"><a href="/jw/bbs/user/register"><i class="fa-solid fa-user-plus"></i>사용자 가입</a></span>
              </h3>
              <hr>
              <div class="row">
                 <div class="col-1"></div>
                 <div class="col-10">
                 <table class="table">
                    <tr>
                       <th>아이디</th>
                     <th>이름</th>
                     <th>이메일</th>
                     <th>등록일</th>
                     <th>액션</th>
                  </tr>
                  <c:forEach var="user" items="${userList}">
                  <tr>
                     <td>${user.uid}</td>
                     <td>${user.uname}</td>
                     <td>${user.email}</td>
                     <td>${user.regDate}</td>
                     <!-- 본인만 수정가능하고, 관리자와 본인이 삭제 가능. 서로 드모르간법칙으로 반대 설정 -->
                     <td>
                        <c:if test="${user.uid eq sessUid}">
                           <a href="/jw/bbs/user/update?uid=${user.uid}"><i class="fa-solid fa-user-pen"></i></a>
                        </c:if> 
                        <c:if test="${user.uid ne sessUid}">
                           <a href="#" class="disabled-link"><i class="fa-solid fa-user-pen"></i></a>
                        </c:if> 
                        <!-- 본인 또는 관리자만 삭제가능 -->
                        <c:if test="${user.uid eq sessUid or sessUid eq 'admin'}">
                           <a class="ms-2" href="javascript:deleteFunc('${user.uid}')"><i class="fa-solid fa-user-minus"></i></a>
                        </c:if> 
                        <c:if test="${user.uid ne sessUid and sessUid ne 'admin'}">
                           <a class="ms-2 disabled-link" href="#"><i class="fa-solid fa-user-minus"></i></a>
                        </c:if>
                     </td>
                  </tr>
                  </c:forEach>
                 </table>
                <%-- pagination https://www.w3schools.com/bootstrap5/bootstrap_pagination.php --%>
              <%--    <ul class="pagination justify-content-center mt-4">
					  <li class="page-item"><a class="page-link" href="#"><i class="fa-solid fa-less-than"></i></a></li>
					  
					  <c:forEach var="page" items="${pageList}">
					  	  <li class="page-item ${currentUserPpage eq page ? 'active' : '' }">
					  	 	<a class="page-link" href="/jw/bbs/user/list?page=${page}">${page}</a>
					  	 	</li>
					  </c:forEach>
					  
					  from here
						<%--<li class="page-item"><a class="page-link" href="#">1</a></li>
					  <li class="page-item active"><a class="page-link" href="#">2</a></li>
					  <li class="page-item"><a class="page-link" href="#">3</a></li>	
					  위의 3줄이 <ul class= ~ </li> 안으로 들어간 형태로 변형시킴
					  <li class="page-item"><a class="page-link" href="#"><i class="fa-solid fa-greater-than"></i></a></li>
				</ul> --%>
        </div>
	      <div class="col-1"></div>
	      </div>
      </div>
   </div>
</div>
<%@ include file="../common/_bottom.jspf"%>
</body>
</html>