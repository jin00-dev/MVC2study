<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>main.jsp(MVC)</h1>
	
			<!-- 로그인 정보없이 페이지 호출하는 경우, 
		로그인페이지로 이동(JSTL) -->
		
	<%-- 	<c:if test="${id == null}">
			<c:redirect url="./MemberLogin.me"/>		
		</c:if> --%>
		<c:if test="${empty id}">
			<c:redirect url="./MemberLogin.me"/>		
		</c:if>	
		
		<!-- 로그인한 사용자의 ID출력 -->		
		<%
// 		   String id = (String) session.getAttribute("id");
// 		   if(id == null){
// 			   response.sendRedirect("./MemberLogin.me");			   
// 		   }
		%>
<%-- 		<h2><%=id %>님 환영합니다! </h2> --%>
		<h2>${sessionScope.id }님 환영합니다! </h2>
		<h2>${id }님 환영합니다! </h2>
		
		<input type="button" value="로그아웃"
		       onclick=" location.href='./MemberLogout.me';">
		
		<hr>
		<h2><a href="./MemberInfo.me">회원정보 조회(내정보 보기)</a></h2>
		
		<hr>
		<h2><a href="./MemberUpdate.me">회원정보 수정</a></h2>
		
		<hr>
		<h2><a href="./MemberDelete.me">회원정보 삭제</a></h2>
		
		<hr>
		<hr>
		<h2><a href="./BoardWrite.bo">게시판 글쓰기(Create)</a></h2>
		
		
		<!-- 관리자 일때 실행 -->
		<hr>
		<h2> 관리자 전용메뉴 </h2>
		<h2><a href="./MemberList.me"> 회원정보 목록(list)</a></h2>
	
	
		
		
		
		
</body>
</html>